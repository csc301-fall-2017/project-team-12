'use strict';
var mongoose = require('mongoose');

var Resume = mongoose.model('Resume');

exports.getAllResumes = function(req, res) {
	Resume.find().populate({
		path: 'tags' 
	}).exec(function(err, task) {
		if(err) res.send(err);
		res.json({resumes: task});
	});
};

exports.insertResume = function(req, res) {
	var resume = new Resume(req.body);
	resume.save(function(err, task) {
		if(err) res.send(err);
		res.json(task);
	});
};