var Recruiter = mongoose.model('Recruiter');

/* Get all the recruiters */
exports.getAllRecruiters = function (req, res){
	Recruiter.find({}, function(err, task)){
		if(err)
			res.send(err);
		res.json(task);
	});
};

exports.addRecruiter = function (req,res){
	var recruiter = new Recruiter(req.body);
	recruiter.save(function(err, task){
		if(err)
			res.send(err);
		res.json(task);
	})
};

exports.deleteRecruiter = function(req, res){
	Recruiter.remove({ :req.param}, function(err, task){
		if(err)
			res.send(err);
		//removed
	})
}

