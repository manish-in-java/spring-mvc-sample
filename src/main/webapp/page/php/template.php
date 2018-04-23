<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Using PHP with Spring Web MVC</title>
  <link href="/c/bootstrap.css" rel="stylesheet" type="text/css"/>
  <script src="/s/jquery.js" type="text/javascript"></script>
  <script src="/s/bootstrap.js" type="text/javascript"></script>
</head>
<body role="document">
<div class="navbar navbar-inverse" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Spring Web MVC</a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Templating <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/php">PHP</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Form Binding <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/form">Binding Java enumerations</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">REST <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/rest.html">Data representations</a></li>
            <li><a href="/graph.html">Object graph</a></li>
            <li><a href="/generics.html">Generics</a></li>
          </ul>
        </li>
        <li><a href="https://github.com/manish-in-java/spring-mvc-sample" target="_blank">Source code</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="container">
  <div class="jumbotron">
    <h1>Using PHP with Spring MVC</h1>
    <p>
      <a href="https://secure.php.net"><code>PHP</code></a> is a scripting language popularly used for generating
      web pages dynamically.  It enjoys widespread adoption by web developers due to its flexibility, gentle
      learning curve and a large ecosystem of libraries and user support groups.
    </p>
  </div>
  <div class="panel panel-primary">
    <div class="panel-heading">
      <h4>PHP templating engine</h4>
    </div>
    <div class="panel-body">
      <p>
        This page has been generated using a PHP template that is parsed and interpreted by
        <a href="http://quercus.caucho.com">Quercus</a>, a Java implementation of PHP.
      </p>
    </div>
    <div class="panel-footer">
      See
      <a href="https://github.com/manish-in-java/spring-mvc-sample/blob/master/src/main/java/org/example/web/view/php/QuercusView.java" target="_blank">QuercusView.java</a>
      for details.
    </div>
  </div>
</div>

<div class="navbar-default">
  <div class="container">
    <p />
    <p class="small text-muted">Copyright 2013-<?php echo date("Y"); ?> SpringSource, Inc. All rights reserved</p>
  </div>
</div>
</body>
</html>
