'use strict';
var mongoose = require('mongoose');

var Tag = mongoose.model('Tag');

/* Get the all the tags for all the companies */
exports.getAllTags = function(req, res) {
	Tag.find({}, function(err, task) {
		if(err) 
			res.send(err);
		res.json(task);
	});
};

/* Insert a new tag for a company */
exports.insertTag = function(req, res) {
	var tag = new Tag(req.body);
	tag.save(function(err, task) {
		if(err) 
			res.send(err);
		res.json(task);
	});
};

/* Remove a tag with a given tag id */
exports.removeTag = function(req, res) {
	Tag.remove({_id: req.params.tagId}, function(err, task) {
		if(err)
			res.send(err);
		res.json({message: 'Successfully deleted the tag'});
	});
};



