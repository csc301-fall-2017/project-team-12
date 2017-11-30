module.exports = function(app) {
	var rc = require('../controllers/CompanyController');

	app.route('/companies')
		.get(rc.getAllCompanies)
		.post(rc.insertCompany);
	

	app.route('/companies/:companyId')
		.delete(rc.removeCompany)
		.put(rc.addRecruiterToCompany);
	}
