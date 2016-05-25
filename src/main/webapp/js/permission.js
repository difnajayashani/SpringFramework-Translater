



$(document).ready(function(){

    $('#myTab').tab(); //initialize tabs
    $("#btnDelt").click(function(){



    });
});





$(function() {
    var baseURL = 'http://yourdomain.com/ajax/';
    //load content for first tab and initialize
    $('#myads').load(baseURL+'home', function() {
        $('#myTab').tab(); //initialize tabs
    });
    $('#myTab').bind('show', function(e) {
        var pattern=/#.+/gi //use regex to get anchor(==selector)
        var contentID = e.target.toString().match(pattern)[0]; //get anchor
        //load content for selected tab
        $(contentID).load(baseURL+contentID.replace('#',''), function(){
            $('#myTab').tab(); //reinitialize tabs
        });
    });
});