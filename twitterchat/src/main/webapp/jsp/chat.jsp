<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<html>
<title>Twitter Chat</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Add some nice styling and functionality.  We'll just use Twitter Bootstrap -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
	<link rel="stylesheet"
	href="../resources/stylesheet/chat.css">
<link href="//abs.twimg.com/favicons/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<style>
body {
	padding-top: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<img src="${userImageUrl}"
											alt="User Avatar" class="img-circle">
						<span class="glyphicon glyphicon-comment"></span> Chat:
						${roomName}  -  Welcome ${user.login}!
						<div class="btn-group pull-right">
							<button type="button"
								class="btn btn-default btn-xs dropdown-toggle"
								data-toggle="dropdown">
								<span class="glyphicon glyphicon-chevron-down"></span>
							</button>
							<ul class="dropdown-menu slidedown">
								<li><a href="./chat"><span
										class="glyphicon glyphicon-refresh"> </span>Refresh</a></li>
								<li class="divider"></li>
								<li><a href="./logout"><span
										class="glyphicon glyphicon-off"></span> Logout</a></li>
							</ul>
						</div>
					</div>
					<div class="panel-body">
							<c:forEach items="${messages}" var="message">
								<c:if test="${message.sender.name == 'Admiin TAI'}">
									<span class="chat-img pull-left">
											<img src="${message.sender.profileImageUrl}"
											alt="User Avatar" class="img-circle">
									</span>
										<div class="chat-body clearfix">
											<div class="header">
												<strong class="primary-font">${message.sender.name}</strong>
												<small class="pull-right text-muted"> <span
													class="glyphicon glyphicon-time"></span>${message.createdAt}</small>
											</div>
											<p> ${message.text}</p>
										</div>
								</c:if>
								<c:if test="${message.sender.name == 'Writer TAI'}">
									<span
										class="chat-img pull-right"> <img
											src="${message.sender.profileImageUrl}" alt="User Avatar"
											class="img-circle">
									</span>
										<div class="chat-body clearfix">
											<div class="header">
												<small class=" text-muted"> <span
													class="glyphicon glyphicon-time"></span>${message.createdAt}</small>
												<strong class="pull-right primary-font">${message.sender.name}</strong>

											</div>
											<p>${message.text}</p>
										</div>
								</c:if>
								<br>
							</c:forEach>
					</div>
					<c:if test="${not empty authorizedToSend}">
						<div class="panel-footer">
							<form name="sentMsg" action="" method="POST"
								accept-charset="UTF-8" role="form">
								<fieldset>
									<div class="input-group">
										<input id="btn-input" type="text" name="msg"
											class="form-control input-sm"
											placeholder="Type your message here..."> <span
											class="input-group-btn">
											<button class="btn btn-warning btn-sm" type="submit"
												id="btn-chat">Send</button>
										</span>
									</div>
								</fieldset>
							</form>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</body>
</html>