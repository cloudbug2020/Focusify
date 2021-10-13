import json
from http import HTTPStatus
from locust import HttpUser, task, between


class ApiUser(HttpUser):
    wait_time = between(2, 5)

    @task(1)
    def post_todos(self):
        payload = {
            "title": "Locust Loadtest Title",
            "description": "Locust Loadtest Description",
            "status": "TODO"
        }

        headers = {'content-type': 'application/json'}
        r = self.client.post("/api/todos/", data=json.dumps(payload), headers=headers)
        assert r.status_code == HTTPStatus.CREATED, "Unexpected response code: " + str(r.status_code)

    @task(2)
    def put_todos(self):
        payload = {
            "title": "Updated Locust Loadtest Title",
            "description": "Updated Locust Loadtest Description",
            "status": "TODO"
        }

        headers = {'content-type': 'application/json'}

        r = self.client.put("/api/todos/1", data=json.dumps(payload), headers=headers)
        assert r.status_code == HTTPStatus.OK, "Unexpected response code: " + str(r.status_code)

    @task(3)
    def get_todos(self):
        r = self.client.get('/api/todos/1')
        assert r.status_code == HTTPStatus.OK, "Unexpected response code: " + str(r.status_code)
