import re
from time import sleep

import requests
from bs4 import BeautifulSoup

from rest.models import Mission, DetailItem

base_url = "http://www.esa.int"

missions_url = base_url + "/ESA/Our_Missions"


def generate():
    r = requests.get(missions_url)
    if r.status_code == 200:
        soup = BeautifulSoup(r.text, 'html.parser')
        for mission in soup.find_all("div", "mis_item"):
            if type(mission) == "NoneType":
                pass
            mis_txt = mission.find("div", "mis_txt")
            mis_img = mission.find("div", "mis_img")

            mission_name = mis_txt.a.span.getText()
            pos = list(re.finditer(r"[0-9]{4}", mis_txt.text))[-1].span()[1]
            mission_description = mis_txt.text[pos:]

            mission_img = base_url + mis_img.img["src"]
            mission_url = mis_img.a["href"]
            if mission_url[0] == '/':
                mission_url = base_url + mission_url
            saveMission(str(mission_name), str(mission_description), str(mission_img), str(mission_url))
            print(mission_name + ": " + mission_description + " -> " + mission_img + " : " + mission_url)



def saveMission(name, description, img, url):
    try:
        m = Mission.objects.get(name=name)
        m.delete()
    except Mission.DoesNotExist:
        pass

    web = ""
    r = requests.get(url)
    if r.status_code == 200:
        soup = BeautifulSoup(r.text, 'html.parser')
        base = soup.find(id="col3_c")

        for a in base.findAll('a'):
            del a['href']

        for i in base.findAll('img'):
            if i['src'][0] == "/":
                i['src'] = base_url + i['src']

        soc = base.find(id="social")
        if soc is not None:
            soc.decompose()


        web = base.find(id="article")
        if web is None:
            web = base
        i = 0
        items = []
        for item in web.find_all(["p", "img"]):
            if item.name == "img":
                src = item["src"]
                if src[0:2] == "..":
                    src = url[:url.rfind("/")+1] + src
                elif src[0] == "/":
                    src = base_url + src
                items.append(("img", src, i))
                i += 1
            else:
                text = ' '.join([str(x) for x in item.stripped_strings])
                if text is not None and text != "" and text != " ":
                    items.append(("p", text, i))
                    i += 1

    m = Mission(name=name, description=description, url=url, img=img)
    m.save()
    for tag, value, num in items:
        DetailItem(num=num, tag=tag, value=value, mission=m).save()

    sleep(0)
