<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITV</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<link href="css/SearchPageBorder.css" rel="stylesheet">

<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />

<!-- 檔案上傳所需的 -->
<script src="js/bootstrap.file-input.js"></script>
<script src="js/js.cookie.js"></script>

  
  <!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->


  <link href="css/HomePage.css" rel="stylesheet">


<link href="assets/css/animate.css" rel="stylesheet">
<link href="assets/css/font-awesome.css" rel="stylesheet">
<link href="assets/css/nexus.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<link href="assets/css/1-col-portfolio.css" rel="stylesheet">

<!-- <link href="http://fonts.googleapis.com/css?family=Roboto:400,300" -->
<!-- 	rel="stylesheet" type="text/css"> -->
<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" -->
<!-- 	rel="stylesheet" type="text/css"> -->


</head>

<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei;">
	
    <jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />



<!-- 東西放在這裡 -->
<br>
<br>
<br>
<br>
<div class="container">
	<div class="row row-no-margin">
	    <!-- Portfolio -->
	    <ul class="portfolio-group">
	        <!-- Portfolio Item -->
	        <li class="portfolio-item col-sm-3 col-xs-6 no-padding" style="height: 139.359px; overflow: hidden;">
	            <a id="article001" href="#articleContent">
	            <figure class="animate fadeInLeft animated">
	                <img src="img/musicvideo.jpg" style="margin-top: -8.45px;">
	            </figure>
	            </a>
	        </li>
	        <!-- Portfolio Item -->
	        <li class="portfolio-item col-sm-3 col-xs-6 no-padding" style="height: 139.359px; overflow: hidden;">
	            <a id="article002" href="#articleContent">
	            <figure class="animate fadeInLeft animated">
	                <img src="img/news.jpg" style="margin-top: -9.36px;">
	            </figure>
	            </a>
	        </li>
	        <!-- Portfolio Item -->
	        <li class="portfolio-item col-sm-3 col-xs-6 no-padding" style="height: 139.359px; overflow: hidden;">
	            <a id="article003" href="#articleContent">
	            <figure class="animate fadeInLeft animated">
	            	<img src="img/animate.jpg" style="margin-top: -10.11px;">
	            </figure>
	            </a>
	        </li>
	        <!-- Portfolio Item -->
	        <li class="portfolio-item col-sm-3 col-xs-6 no-padding" style="height: 139.359px; overflow: hidden;">
	            <a id="article004" href="#articleContent">
	            <figure class="animate fadeInLeft animated">
	            	<img src="img/idol.jpg" style="margin-top: -6.53px;">
	            </figure>
	            </a>
	        </li>
	    </ul>
	    </div>
	</div>
                        
<br>
<br>

<div id="articleContent" class="container">
	<!-- Project One -->
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/airu.jpg" alt="">
	        </a>
	    </div>
	    
	    <div class="col-md-9">
	        <a href="#"><h2>ArticleTitle</h2></a>
	        <h5 style="display: inline; ">MemberAccount</h5><span style="float: right;">date</span>
	        <p>id="articleContent"</p>
	    </div>
	</div>
	</div>
	<!-- /.row -->
	
	<hr>
	
	<!-- Project Two -->
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/icecream.jpg" style="margin: auto;">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Two</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ut, odit velit cumque vero doloremque repellendus distinctio maiores rem expedita a nam vitae modi quidem similique ducimus! Velit, esse totam tempore.</p>
	    </div>
	</div>
	</div>
	<!-- /.row -->
	
	<hr>
	
	<!-- Project Three -->
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/cho.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Three</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis, temporibus, dolores, at, praesentium ut unde repudiandae voluptatum sit ab debitis suscipit fugiat natus velit excepturi amet commodi deleniti alias possimus!</p>
	    </div>
	</div>
	</div>
	<!-- /.row -->
	
	<hr>
	
	<!-- Project Four -->
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/rainbow.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Four</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, quidem, consectetur, officia rem officiis illum aliquam perspiciatis aspernatur quod modi hic nemo qui soluta aut eius fugit quam in suscipit?</p>
	    </div>
	</div>
	</div>
	<!-- /.row -->
	
	<hr>
	
	<!-- Project Five -->
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/pe.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Five</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, quo, minima, inventore voluptatum saepe quos nostrum provident ex quisquam hic odio repellendus atque porro distinctio quae id laboriosam facilis dolorum.</p>
	    </div>
	</div>
	</div>
	<hr>
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/pe.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Five</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, quo, minima, inventore voluptatum saepe quos nostrum provident ex quisquam hic odio repellendus atque porro distinctio quae id laboriosam facilis dolorum.</p>
	    </div>
	</div>
	</div>
	<hr>
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/pe.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Five</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, quo, minima, inventore voluptatum saepe quos nostrum provident ex quisquam hic odio repellendus atque porro distinctio quae id laboriosam facilis dolorum.</p>
	    </div>
	</div>
	</div>
	<hr>
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/pe.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Five</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, quo, minima, inventore voluptatum saepe quos nostrum provident ex quisquam hic odio repellendus atque porro distinctio quae id laboriosam facilis dolorum.</p>
	    </div>
	</div>
	</div>
	<hr>
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/pe.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Five</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, quo, minima, inventore voluptatum saepe quos nostrum provident ex quisquam hic odio repellendus atque porro distinctio quae id laboriosam facilis dolorum.</p>
	    </div>
	</div>
	</div>
	<hr>
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/pe.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Five</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, quo, minima, inventore voluptatum saepe quos nostrum provident ex quisquam hic odio repellendus atque porro distinctio quae id laboriosam facilis dolorum.</p>
	    </div>
	</div>
	</div>
	<hr>
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/pe.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Five</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, quo, minima, inventore voluptatum saepe quos nostrum provident ex quisquam hic odio repellendus atque porro distinctio quae id laboriosam facilis dolorum.</p>
	    </div>
	</div>
	</div>
	<hr>
	<div class="row">
	<div class="animate fadeInLeft animated">
	    <div class="col-md-3" style="height: 142.188px; overflow: hidden;">
	        <a href="#">
	            <img class="img-responsive" src="img/pe.jpg" alt="">
	        </a>
	    </div>
	    <div class="col-md-9">
	        <h3>Project Five</h3>
	        <h4>Subheading</h4>
	        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, quo, minima, inventore voluptatum saepe quos nostrum provident ex quisquam hic odio repellendus atque porro distinctio quae id laboriosam facilis dolorum.</p>
	    </div>
	    
	</div>
	</div>
</div>
<br>
<br>

<script type="text/javascript" src="assets/js/scripts.js"></script>
<script type="text/javascript" src="assets/js/jquery.isotope.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/js/jquery.slicknav.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/js/jquery.visible.js" charset="utf-8"></script>
<script type="text/javascript" src="assets/js/jquery.sticky.js" charset="utf-8"></script>
<script type="text/javascript" src="assets/js/slimbox2.js" charset="utf-8"></script>
<script src="assets/js/modernizr.custom.js" type="text/javascript"></script>
<script src="assets/js/article.js" type="text/javascript"></script>
<script src="ckeditor/ckeditor.js" type="text/javascript"></script>

	<jsp:include page="/Footer.jsp" />
	
</body>
</html>