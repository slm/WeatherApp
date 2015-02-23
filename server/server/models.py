from django.db import models
from django.utils import timezone

class tempMessage(models.Model):
    text = models.CharField(max_length=200)
    pub_date = models.DateTimeField('date published')
    sending_user_link = models.CharField(max_length=200);
    sending_user_name = models.CharField(max_length=200);
    temperature = models.IntegerField()
    like = models.IntegerField()
    dislike = models.IntegerField()
    isactive = models.BooleanField(default=False)

    def was_published_recently(self):
        return self.pub_date >= timezone.now() - self.pub_date.timedelta(days=1)

    class Meta:
        verbose_name = ('message')
        verbose_name_plural = ('messages')
