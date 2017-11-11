import re
import requests
from bs4 import BeautifulSoup

from rest.models import Mission

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
            saveMission(mission_name, mission_description, mission_img, mission_url)
            print(mission_name + ": " + mission_description + " -> " + mission_img + " : " + mission_url)



def saveMission(name, description, img, url):
    try:
        m = Mission.objects.get(name=name)
        m.delete()
    except Mission.DoesNotExist:
        pass
    m = Mission(name=name, description=description, url=url, img=img)
    m.save()