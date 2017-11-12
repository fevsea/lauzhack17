from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns

from rest import views
from rest.views import MissionList

from rest_framework import routers


urlpatterns = [
    url(r'^$', views.api_root, name="root"),
    url(r'^missions/$', MissionList.as_view(), name="missions_list"),
    url(r'^missions/(?P<pk>[0-9]+)/$', views.MissionDetail.as_view(), name="mission_detail"),
]

urlpatterns = format_suffix_patterns(urlpatterns)
