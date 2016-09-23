<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="//d3plus.org/js/d3.js"></script>
<script src="//d3plus.org/js/d3plus.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="viz"></div>

<script>
  // create sample dataset
  var entity_store = [
    {"name": "alpha", "size": 10},
    {"name": "beta", "size": 12},
    {"name": "gamma", "size": 30},
    {"name": "delta", "size": 26},
    {"name": "epsilon", "size": 12},
    {"name": "zeta", "size": 26},
    {"name": "theta", "size": 11},
    {"name": "eta", "size": 24}
  ]
  // create list of node positions
  var positions = [
    {"name": "alpha", "x": 10, "y": 15},
    {"name": "beta", "x": 12, "y": 24},
    {"name": "gamma", "x": 16, "y": 18},
    {"name": "delta", "x": 26, "y": 21},
    {"name": "epsilon", "x": 13, "y": 4},
    {"name": "zeta", "x": 31, "y": 13},
    {"name": "theta", "x": 19, "y": 8},
    {"name": "eta", "x": 24, "y": 11}
  ]
  // create list of node connections
  var graph_store = [
    {"source": "alpha", "target": "beta"},
    {"source": "alpha", "target": "gamma"},
    {"source": "beta", "target": "delta"},
    {"source": "beta", "target": "epsilon"},
    {"source": "zeta", "target": "gamma"},
    {"source": "theta", "target": "gamma"},
    {"source": "eta", "target": "gamma"}
  ]
  // instantiate d3plus
  var visualization = d3plus.viz()
    .container("#viz")  // container DIV to hold the visualization
    .type("network")    // visualization type
    .data(entity_store)  // sample dataset to attach to nodes
    .nodes(positions)   // x and y position of nodes
    .edges(graph_store) // list of node connections
    .size("size")       // key to size the nodes
    .id("name")         // key for which our data is unique on
    .draw()             // finally, draw the visualization!
</script>

</body>
</html>