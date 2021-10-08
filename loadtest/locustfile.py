import datetime, json
from http import HTTPStatus
from locust import HttpUser, task, between

class ApiUser(HttpUser):
    wait_time = between(2, 5)

    @task(1)
    def post_todos(self):
     payload = {
                   "title": "Postman Collection Title",
                   "description": "Postman Collection Description",
                   "status": "TODO"
               }

     headers = {'content-type': 'application/json'}

     r = self.client.post("/todos/", data=json.dumps(payload), headers=headers)
     assert r.status_code == HTTPStatus.OK, "Unexpected response code: " + str(r.status_code)

    @task(3)
    def get_todos(self):
        r = self.client.get('/todos/1')
        assert r.status_code == HTTPStatus.OK, "Unexpected response code: " + str(r.status_code)
