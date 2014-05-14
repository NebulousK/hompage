<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Responsive Aside website template</title>
<link rel="stylesheet" href="/homepage/css/components.css" type="text/css" charset="utf-8">
<link rel="stylesheet" href="/homepage/css/responsee.css" type="text/css" charset="utf-8">
<!-- CUSTOM STYLE -->
<link rel="stylesheet" href="/homepage/css/template-style.css">
<script type="text/javascript" src="/homepage/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/homepage/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/homepage/js/modernizr.js"></script>
<script type="text/javascript" src="/homepage/js/responsee.js"></script>
</head>
<body class="size-1140">
	<!-- TOP NAV WITH LOGO -->
	<header>
		<nav>
			<div class="line">
				<div class="s-12 l-2">
					<img class="s-5 l-12 center"
						src="./Responsive Aside website template_files/logo.png">
				</div>
				
			</div>
		</nav>
	</header>
	<!-- ASIDE NAV AND CONTENT -->
	<div class="line">
		<div class="box  margin-bottom">
			<div class="margin">
				<!-- ASIDE NAV 2 -->
				<aside class="s-12 l-3">
					<jsp:include page="/menu.html"/>
				</aside>
				<!-- CONTENT -->
				<section class="s-12 l-6">
					<h1>Content</h1>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
						sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna
						aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud
						exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea
						commodo consequat. Duis autem vel eum iriure dolor in hendrerit in
						vulputate velit esse molestie consequat, vel illum dolore eu
						feugiat nulla facilisis at vero eros et accumsan et iusto odio
						dignissim qui blandit praesent luptatum zzril delenit augue duis
						dolore te feugait nulla facilisi. Nam liber tempor cum soluta
						nobis eleifend option congue nihil imperdiet doming id quod mazim
						placerat facer possim assum. Typi non habent claritatem insitam;
						est usus legentis in iis qui facit eorum claritatem.
						Investigationes demonstraverunt lectores legere me lius quod ii
						legunt saepius. Claritas est etiam processus dynamicus, qui
						sequitur mutationem consuetudium lectorum. Mirum est notare quam
						littera gothica, quam nunc putamus parum claram, anteposuerit
						litterarum formas humanitatis per seacula quarta decima et quinta
						decima. Eodem modo typi, qui nunc nobis videntur parum clari,
						fiant sollemnes in futurum.</p>
					<h2>Sub Header</h2>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
						sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna
						aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud
						exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea
						commodo consequat. Duis autem vel eum iriure dolor in hendrerit in
						vulputate velit esse molestie consequat, vel illum dolore eu
						feugiat nulla facilisis at vero eros et accumsan et iusto odio
						dignissim qui blandit praesent luptatum zzril delenit augue duis
						dolore te feugait nulla facilisi. Nam liber tempor cum soluta
						nobis eleifend option congue nihil imperdiet doming id quod mazim
						placerat facer possim assum. Typi non habent claritatem insitam;
						est usus legentis in iis qui facit eorum claritatem.
						Investigationes demonstraverunt lectores legere me lius quod ii
						legunt saepius. Claritas est etiam processus dynamicus, qui
						sequitur mutationem consuetudium lectorum. Mirum est notare quam
						littera gothica, quam nunc putamus parum claram, anteposuerit
						litterarum formas humanitatis per seacula quarta decima et quinta
						decima. Eodem modo typi, qui nunc nobis videntur parum clari,
						fiant sollemnes in futurum.</p>
				</section>
				<!-- ASIDE NAV 2 -->
				<aside class="s-12 l-3">
					<jsp:include page="/Friend.jsp"/>
				</aside>
			</div>
		</div>
	</div>
	<!-- FOOTER -->
	<footer class="box">
		<jsp:include page="/footer.html"/>
	</footer>

</body>
</html>