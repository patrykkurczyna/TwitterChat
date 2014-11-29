<!DOCTYPE html>
<html lang="pl_PL">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<h1>Hello, world!</h1>
	<div class="container">
		<!-- Przesuniecie drugiej kolumny o 4 pola od pierwszej -->
		<div class="row">
			<div class="col-md-4">...</div>
			<div class="col-md-4 col-md-offset-4">...</div>
		</div>

		<!-- Przesuniecie pierwszej kolumny o 3 pola od marginesu i drugiej kolumny o 3 pola od pierwszej -->
		<div class="row">
			<div class="col-md-3 col-md-offset-3">3 offset 3</div>
			<div class="col-md-3 col-md-offset-3">3 offset 3</div>
		</div>

		<!-- Przesuniecie kolumny o 3 pola od marginesu -->
		<div class="row">
			<div class="col-md-6 col-md-offset-3">...</div>
		</div>

		<!-- Pierwsza kolumna o szerokości 2 normalnie, druga kolumna o szerokości 6 i przesunięta o 2, trzecia kolumna o szerokości 1 i przesunięta o 1 -->
		<div class="row">
			<div class="col-md-2">col-md-2</div>
			<div class="col-md-6 col-md-offset-2">col-md-6 col-md-offset-2</div>
			<div class="col-md-1 col-md-offset-1">col-md-1 col-md-offset-1</div>
		</div>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>