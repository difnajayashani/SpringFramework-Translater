$(document).ready(function () {

    $.ajax({
        type: "POST",
        url: "PopulateUserServlet",
        dataType: "json",

        success: function (result) {

            $('#table').bootstrapTable({
                //pagination: true,
                //pageSize: 10,
               // pageList: [10, 25, 50, 100, 200],
                //search: true,
                showColumns: true,
                showRefresh: true,
                showToggle:true,
                //detailView:true,
                //detailFormatter: detailFormatter,
                clickToSelect: true,

                singleSelect: true,
                toolbar: '#toolbar',

                minimumCountColumns: 3,
                columns: [/*{
                    field: 'state',
                    checkbox: true

                },*/{
                    field: 'id',
                    title: 'ID :',
                    sortable: true,
                    align:'left'
                },{
                    field: 'user_name',
                    title: 'Username :',
                    sortable: true,
                    align:'left',
                    searchable:true
                }, {
                    field: 'f_name',
                    title: 'First Name :',
                    align:'left',
                    sortable: true
                }, {
                    field: 'l_name',
                    title: 'Last Name :',
                    align:'left',
                    sortable: true
                }, {
                    field: 'niceDate',
                    title: 'DOB :',
                    align:'left',
                    sortable: true
                }, {
                    field: 'mobile',
                    title: 'Phone No :',
                    align:'left'
                }, {
                    field: 'e_mail',
                    title: 'Email :',
                    align:'left'
                }, {
                    field: 'country',
                    title: 'Country :',
                    align:'left'
                }, {
                    field: 'city_id',
                    title: 'City :',
                    align:'left'
                },/*{
                    field: 'group',
                    title: 'Role :',
                    align:'left'
                },*/ {
                    field: 'Options',
                    title: 'Options :',
                    align: 'center',
                    formatter: operateFormatter,
                    events: operateEvents
                }],
                data: result
            });
        }
    });

})

/*
$(document).ready(function () {

    *//*
     * typeahead function
     *//*
    $("#user-search").keyup(function () {
        var sname = $("#user-search").val();

        $.ajax({
            type: "POST",
            url: "TypeAheadServlet",
            dataType: "json",
            data: {"sname": sname},
            success: function (data) {
                //alert("Usname value"+ data);
                $('#user-search').typeahead({
                    source: data


                });
            }
        })
    })

})*/

$(document).ready(function () {

    // $("#searchbtn").click(function () {
    $("#user-search").keyup(function () {
        var snamef = $("#user-search").val();

        $.ajax({
            type: "POST",
            url: "SearchUserServlet",
            dataType: "json",
            data: {"snamef": snamef},
            success: function (data) {
                //alert("output for searcj button"+ data);
                $('#table').bootstrapTable('load', data);
            }
        })
    })

})




function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="#" title="Like" >',
        '<em class="fa fa-pencil"></em>',
        '</a>  ',
        '<a class="remove" href="#" title="Delete">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click .like': function (e, value, row, index) {
        var data1 = JSON.stringify(row);
        var objc1 = JSON.parse(data1);

        $('#update-form-username').val(objc1["user_name"]);
        $('#update-first-name').val(objc1["f_name"]);
        $('#update-last-name').val(objc1["l_name"]);
        $('#date2').val(objc1["niceDate"]);
        $('#update-form-mobile').val(objc1["mobile"]);
        $('#update-form-email').val(objc1["e_mail"]);
        $('#update-country.value').val(objc1["country"]);
        $('#update-form-city').val(objc1["city_id"]);
        //$('#update-password').val(objc1["niceDate"]);


        $('#myModal2').modal('show');

        //alert('You click like action, row: ' + data1);

    },

    'click .remove': function (e, value, row, index) {
        var data2 = JSON.stringify(row);
        var objc2 = JSON.parse(data2);
        $('#lblUname').text(objc2["user_name"]);
        $('#myModal1').modal('show');

        //alert('You click like action, row: ' + objc["user_name"]);

    }
};

$(document).ready(function(){
    $("#btnDelt").click(function(){
        var val=$("#lblUname").text();
        // var val = 'difna';

//        alert("Error in deleting !" +val);
        $.ajax({

            type:"POST",
            url:"DeleteUserServlet",
            data:{"val":val},

            success:function(msg){

                if(msg==1){
                    alert("Deleted user !");
                    $("#myModal1").modal('hide');

                }else{
                    alert("Error in deleting !");
                    $("#myModal1").modal('hide');
                }
            }
        })

    });
});


$(document).ready(function(){
    $("#updatebtn").click(function(){

        var user=$("#update-form-username").val();

        var uf_name = $("#update-first-name").val();
        var ul_name = $("#update-last-name").val();
        var udate = $("#date2").val();
        var ucountry = $("#update-country").val();
        var ucity = $("#update-city").val();
        var uemail = $("#update-form-email").val();
        var umobile = $("#update-form-mobile").val();
        var upw = $("#update_password").val();
        var ugroup = $("#ugroup").val();

       // alert("Updated User details of " + ucity);

        $.ajax({

            type:"POST",
            url:"UpdateUserServlet",
            data:{"uu_name":user,"uf_name":uf_name,"ul_name":ul_name,"udate":udate ,"ucountry":ucountry,"ucity":ucity,
                "uemail":uemail,"umobile":umobile,"upw":upw, "ugroup":ugroup},

            success:function(msg){

                if(msg==1){
                    alert("Updated User details of " + user);


                }else{
                    alert("Error in updating!");
                    $("#myModal1").modal('hide');
                }
            }
        })
        ;
    });
});


<!--javascript to load the cities pertaing to the country in update form -->

$(document).ready(function(){
    $("#update-country").change(function () {

        var country = $(this).val();

        $.ajax({
            type: "POST",
            url: "LoadCityServlet",
            dataType: "JSON",
            data: {"country": country},
            success: function (data) {

                var  formCity = $("#update-city"), option = "";
                formCity.empty();

                for (var C = 0; C < data.length; C++) {
                    option = option + "<option value='" + data[C].cityName + "'>" + data[C].cityName + "</option>";
                }
                formCity.append(option);
            }
        })
    })

});

