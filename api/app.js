var express  = require('express');
var connect = require('connect');
var app      = express(),
 port     = process.env.PORT || 8080;
 
var bodyParser = require ('body-parser');
    app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));
//GET all goals
app.get('/goals',function(req,res){
    db.find({}).sort({
        updated: -1
    }).exec(function(err,goals){
        if(err) res.send(err);
        res.json(goals);
    });
});

//POST a new goal
app.post('/goals', function(req, res) {
  var goal = {
    description: req.body.description,
  };
  db.insert(goal, function(err, goal) {
    if (err) res.send(err);
    res.json(goal);
  });
});

//DB setup
var Datastore =require('nedb');
var db=new Datastore({
    filename: 'goals.db',
    autoload: true,
    timestampData:true
});

//DB check

var goal= {
    description: 'Do xyz',
};

//DB insertion

db.insert(goal,function(err,newGoal){
    if(err) console.log(err);
    console.log(newGoal);
});

// Routes
 
app.get('/',function(req,res){
        res.send('Our first');
        });
 
app.listen(port,function(){
    console.log('listen'+port);
});
 