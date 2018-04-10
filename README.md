# lauzhack17

## What it is
This project has two parts witch correspond to frontend and backend. Both parts are in diferents branches on this repo.

Basically is an android app that consumes a REST API to show a list with all the missions of ESA (European Space Agency).

## Code location
Code can be found in `frontend` and `backend` branches.

## What technologies do it uses
At backend: Python, Django, DjangoRestFramework, BeautifulSoup, SQLite
At frontend Java, Android, retrofit

## How to run it

The backend can be run in a local server using ```python3 manage.py runserver 0.0.0.0:8000```
In order to generate the database the following commands must bne executed:

- ``` python manage.py migrate```

- ``` python rest/scraper.py```

## API

- /misions/
- /missions/<pk>

### Example json
```json
{
    "pk": 1248,
    "name": "Athena",
    "description": "Probing the hot and energetic Universe",
    "url": "http://sci.esa.int/cosmic-vision/54517-athena/",
    "img": "http://www.esa.int/var/esa/storage/images/esa_multimedia/images/2013/11/artist_s_impression_of_an_active_galaxy/13427405-1-eng-GB/Artist_s_impression_of_an_active_galaxy_mission.jpg",
    "web": [
        {
            "num": 0,
            "tag": "p",
            "value": "Mission Summary Athena Advanced Telescope for High-E..."
         },
         {
            "num": 1,
            "tag": "img",
            "value": "http://sci.esa.int/plato/../../../science-e-media/img/6b/59243_merging_black_holes_170.jpg"
          }  ]
 }
