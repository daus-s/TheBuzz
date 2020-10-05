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

function printFeed(JSON)
{
    //for each ... finish this up tommo with the printing the list of posts from database
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

function checkLogIn()
{

}

function radioCheck()
{
    var radio = document.getElementByName("type");

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radio[i].checked)
        {
            if (radio[i].value=="uni")
            {
                document.getElementById("url").href = "/creategroup/university";
                break;
            }
            if (radio[i].value=="business")
            {
                document.getElementById("url").href = "/creategroup/business";
                break;
            }
            if (radio[i].value=="club")
            {
                document.getElementById("url").href = "/creategroup/club";
                break;
            }
            // only one radio can be logically checked, don't check the rest

        }
    }
}

function universityDataList()
{

}


var uList =
{
"university-domain":[
{"name":"University of Washington","domain":"uw.edu"}
,{"name":"Central Washington University","domain":"cwu.edu"}
,{"name":"Chapman University","domain":"chapman.edu"}
]}
