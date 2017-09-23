---
layout: default
---
{% include_relative README.md %}
## Exercises:
<ul>
  {% for solution in site.solutions %}
    <li><a href="{{ solution.url }}">{{ solution.title }}</a></li>
  {% endfor %}
</ul>
