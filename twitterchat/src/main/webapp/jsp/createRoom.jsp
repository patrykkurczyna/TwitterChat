<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <title>Twitter chat - create room</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Add some nice styling and functionality.  We'll just use Twitter Bootstrap -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
    <link href="//abs.twimg.com/favicons/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <style>
        body{padding-top:20px;}
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
        	
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Create chat room</h3>
                    </div>
                    <div class="panel-body">
                        <form name="loginform" action="" method="POST" accept-charset="UTF-8" role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Room Name" name="roomName" type="text">
                                </div>
                                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Create room">
                            </fieldset>
                            <br>
                            <c:if test="${roomCreated}" >
                                	<div class="alert alert-success" role="alert">
									  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
									  <span class="sr-only">Success: </span>
									  Success: room created!
									</div>
                            </c:if>
                            <c:if test="${roomCreated == false}" >
                                	<div class="alert alert-danger" role="alert">
									  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
									  <span class="sr-only">Error: </span>
									  Room name cannot be empty!
									</div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</body>
</html>