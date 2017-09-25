---
layout: default
---
{% include_relative README.md %}
## Exercises:
<ul>
  {% for solution in site.solutions %}
    <li><a href="{{ solution.url }}">{{ solution.title }}</a><br>{{solution.description}}</li>
  {% endfor %}
</ul>
