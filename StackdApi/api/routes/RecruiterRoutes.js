module.exports = function(app) {
	var rc = require('../controllers/RecruiterController');

	app.route('/recruiters')
		.get(rc.getAllRecruiters)
		.post(rc.addRecruiter);

	app.route('/recruiters/:recruiterId')
		.put(rc.addToCollectedResumes);
}
