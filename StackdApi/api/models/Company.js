var mongoose = require('mongoose');

var CompanySchema = new mongoose.Schema({
	cId :{
		type: Number,


	}
	companyName : {
		type: String
	}
});

module.export = mongoose.model('Company', CompanySchema)