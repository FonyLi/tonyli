/**
 * 聊天服务。
 */

var socketio = require('socket.io');

var io;
var guestNumber = 1;                     //初始用户名编号
var nickNames = {};                      // 昵称列表
var namesUsed = [];                      //使用过的用户名
var currentRoom = {};                    //当前聊天室

function assignGuestName(socket, guestNumber, nickNames, namesUsed) {
	var name = 'Guest' + guestNumber;
	nickNames[socket.id] = name;
	socket.emit('nameResult', {
		success: true,
		name: name
	});
	namesUsed.push(name);
	return guestNumber + 1;
}

function joinRoom(socket, room) {
	socket.join(room);
	currentRoom[socket.id] = room;
	socket.emit('joinResult', {room: room});
	socket.broadcast.to(room).emit('message', {
		text: nickNames[socket.id] + 'has joined ' + room + '.'
	});
}

function handleMessageBroadcasting(socket) {
	socket.on('message', function(message) {
		socket.broadcast.to(message.room).emit('message', {
			text: nickNames[socket.id] + ':' + message.text
		});
	});
}

exports.listen = function(server) {
	io = socketio.listen(server);
	io.set('log level', 1);
	// 定义每个用户的连接处理
	io.sockets.on('connection', function(socket) {
		// 分配一个用户名
		guestNumber = assignGuestName(socket, guestNumber, nickNames, namesUsed);
		// 将用户加入聊天室Lobby里
		joinRoom(socket, 'Lobby');
		//处理聊天信息
		handleMessageBroadcasting(socket, nickNames);
		//handleNameChangeAttempts(socket, nickNames, namesUsed);
		//handleRoomJoining(socket);
		//handleClientDisconnection(socket, nickNames, namesUsed);
	});
};