<!DOCTYPE html>
<html>
<head>
  <%--  <script src="http://code.jquery.com/jquery.min.js"></script>
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" type="text/css" />
    <script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>--%>

    <script type="text/javascript" src="http://davidstutz.github.io/bootstrap-multiselect/dist/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="http://davidstutz.github.io/bootstrap-multiselect/dist/css/bootstrap-multiselect.css" type="text/css"/>

    <meta charset="utf-8">
    <title>Example by @Bneiluj</title>

    <script src="js/adduser.js"></script>
</head>
<body>

<select class="form-control " id="insightList" multiple="multiple">

        <option value="1-1">Option 1.1</option>
        <option value="2-1">Option 2.1</option>
        <option value="2-2">Option 2.2</option>
        <option value="2-3">Option 2.3</option>

</select>

<script id="example">
    $('#insightList').multiselect({
        enableClickableOptGroups: true
    });
</script>