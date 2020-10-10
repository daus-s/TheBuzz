function setCookie(cname, cvalue, exdays)
{
      var d = new Date();
      d.setTime(d.getTime() + (exdays*24*60*60*1000));
      var expires = "expires="+ d.toUTCString();
      document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname)
{
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++)
    {
        var c = ca[i];
        while (c.charAt(0) == ' ')
        {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0)
        {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function printFeed(listPost)
{
    listPost.forEach(toFeedObj);
}


function validPassword()
{
    if (document.getElementById('pwd1').value == document.getElementById('pwd2').value)
    {
        document.getElementById('equal').style.color = 'green';
        document.getElementById('equal').innerHTML = 'matching';
        document.getElementById('submit').disabled = false;
    }
    else
    {
        document.getElementById('equal').style.color = 'red';
        document.getElementById('equal').innerHTML = 'not matching';
        document.getElementById('submit').disabled = true;

    }

    if (document.getElementById('pwd1').value.length < 8)
    {
        document.getElementById('valid').style.color = 'red';
        document.getElementById('valid').innerHTML = 'not valid';
        document.getElementById('submit').disabled = true;
    }
    else
    {
        document.getElementById('valid').style.color = 'green';
        document.getElementById('valid').innerHTML = 'valid';
        document.getElementById('submit').disabled = false;

    }

}

function checkLogIn(path)
{
    var c1 = getCookie("loginStatus");
    if (c1=="logged-in")
    {
        document.location.href = path;
    }
    document.location.href = "/login";
}

function radioCheck()
{
    if (document.getElementById('business').checked)
    {
        document.getElementById("url").href = "/creategroup/business";
    }
    else if (document.getElementById('university').checked)
    {
        document.getElementById("url").href = "/creategroup/university";
    }
    else if (document.getElementById('club').checked)
    {
        document.getElementById("url").href = "/creategroup/club";
    }
    else
    {
        document.getElementById("url").href = "/creategroup";
    }
    return true;
}

function universityDataList(obj)
{
    var list = obj.university_domain;

    document.getElementById("listU").innerHTML = "";
    list.forEach(makeDataListOption);


}

function makeDataListOption(str, index)
{
    document.getElementById("listU").innerHTML += "<option value=\"" + str.name + "\"><br>";
}

function toFeedObj(str, index)
{
    document.body.innerHTML += "<a href=page/"+ str.publisher + ">" + str.publisher + "</a><brr>";
    document.body.innerHTML += "<img src=\"" + str.imageLocation + "\" alt=\"" + str.caption + "\"><br>";
    document.body.innerHTML += str.caption + "<br>";
}
