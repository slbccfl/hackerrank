---
layout: default
---
{% include_relative README.md %}
## Exercises:
<ul>
  {% for solution in site.solutions %}
    <li><a href="{{ solution.url | relative_url }}">{{ solution.title }}</a><br>{{solution.description}}</li>
  {% endfor %}
</ul>
