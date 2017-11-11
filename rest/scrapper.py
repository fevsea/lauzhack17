# -*- coding: utf-8 -*-
import os, django

from datetime import datetime, timedelta

os.environ.setdefault("DJANGO_SETTINGS_MODULE", "backend.settings")
django.setup()

from rest.soup import missions


missions.generate()
