package com.example_app.pricyapp.retrofit.model

data class GoldModel(

    val message:  String,
    val data: Alldata

)





data class Alldata(

    val golds : List<ContentModel>,
    val currencies: List<ContentModel>


)

data class ContentModel(

    val label : String,
    val price :Int


)