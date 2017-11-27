'use strict';

module.exports = function(app) {
	var tc = require('../controllers/TagController');

	app.route('/tags')
		.get(tc.getAllTags)
		.post(tc.insertTag);

	app.route('/tags/:tagId')
		.delete(tc.removeTag);
};