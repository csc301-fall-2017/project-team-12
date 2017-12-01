'use strict';

module.exports = function(app) {
	var tc = require('../controllers/ResumeController');
	
	app.route('/resumes')
		.get(tc.getAllResumes)
		.post(tc.insertResume);
};