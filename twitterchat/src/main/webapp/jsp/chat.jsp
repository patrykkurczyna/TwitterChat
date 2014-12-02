<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<html>
<title>Twitter chat : Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Add some nice styling and functionality.  We'll just use Twitter Bootstrap -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
<style>
body {
	padding-top: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-comment"></span> Chat -
						${roomName}
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
						<ul class="chat">
							<c:set var="i" scope="session" value="${0}" />
							<c:forEach items="${messages}" var="message">
								<c:if test="${i % 2 == 0}">
									<li class="left clearfix"><span class="chat-img pull-left">
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
										</div></li>
								</c:if>
								<c:if test="${i % 2 == 1}">
									<li class="right clearfix"><span
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
										</div></li>
								</c:if>
								<br>
								<c:set var="i" scope="session" value="${i + 1}" />
							</c:forEach>
						</ul>
					</div>
					<c:if test="${not empty authorizedToSend}">
						<div class="panel-footer">
							<form name="sentMsg" action="" method="POST"
								accept-charset="UTF-8" role="form">
								<fieldset>
									<div class="input-group">

										<input id="btn-input" type="text"
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