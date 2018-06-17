var express=require('express');
const body_parser=require("body-parser");
const mongoose=require('mongoose');
const Schema =mongoose.Schema;// var MongoClient=require('mongodb').MongoClient;
var app=express();
app.use(body_parser.urlencoded({extended:true}));
app.use(body_parser.json())
mongoose.connect('mongodb://localhost/test')
.then(()=>{
	console.log("connected successfully")
})
.catch(err=>{
	console.log(err)
})
const Cart = new Schema({
	name: {type:String,required: true},
	//quantity: {type:Number,required: true},
	price: {type:Number,required:true}
});
const mod= mongoose.model('cartItems',Cart);
app.post('/items',(req,res)=>{
   console.log('posting items');
   var cartItems=req.body;
	var venk = new mod(
		{
			name:cartItems.name,
			price:cartItems.price,
	//		quantity:cartItems.quantity
		});
		venk.save(function(error){
		if(error)
		{
			res.status(500).send('Failed to save data: ' + error )
			return;	
		}
		else{
			res.status(200).send('Successfully saved data ');
        }
	});
})
app.get('/viewitems',function(req,res){
	 mod.find({}).then(function(result){
		 console.log(result);
		 res.status(200).json(result);
	 })
})
var ser=app.listen(8080,function(){
	console.log("server is up"+ser.address().port);
})
