import java.io.File
 

    val iconDir = File("icons")
    val outFile = File("news_tube.html")

    val linkFile = File("links.txt")
    var lines : List<String>  = linkFile.readLines()

    var html = getHtmlHeader()

    lines.forEach {  line ->
             if(line.trim().contains("   ")){
            var (name,url) = line.trim().split(Regex(" +"))


            val icon = File(iconDir,"$name.jpg")

            if(icon.exists()){

                if(! url.contains("http"))
                     url = "http://$url"


                html+="<a href='$url'   target='_blank'  ><img src='$icon'  ></a>\n"

            }else{
                //println(icon)
            }

        }else if(line.contains("span") && line.contains("<")){
            html+=line+"\n"
        }



    }


    println(html)

    outFile.writeText(html)


 



fun getHtmlHeader() = """

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>News Channels</title>
<style>
img {
   border: 3px solid #ddd;
   height: 120;
   width: 120;
   padding: 4px;
   margin: 6px;
   border-radius: 5%;
   box-shadow: 8px 8px 8px -5px  black;
}

.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding:  8px 14px;
  transition: 0.3s;
  font-size: 15px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
   /*  
   display: none;  
  padding: 4px 4px;
  border: 0px solid #ccc;
  border-top: none;
  */
}

</style>
<script>
function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}
</script>
 </head>  
 
 
<body  >

<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'Canada')">Canada</button>
  <button class="tablinks" onclick="openCity(event, 'Business')">Business</button>
  <button class="tablinks" onclick="openCity(event, 'English')">English</button>
  <button class="tablinks" onclick="openCity(event, 'US Local')">US Local</button>
   <button class="tablinks" onclick="openCity(event, 'UK')">UK</button>
   <button class="tablinks" onclick="openCity(event, 'Other')">Other</button>
 
</div>

""".trimIndent()


