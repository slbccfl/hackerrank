#!/bin/ruby
require 'pry'


a = gets.strip
b = gets.strip

x = a.split(//)
y = b.split(//)

x.sort!
y.sort!

delCount = 0
j = 0
# binding.pry
while j < [x.length, y.length].min do
    # puts "#{j} of #{[x.length, y.length].min}"
    # puts "x: #{x} - y: #{y}"
    if x[j] == y[j]
        j += 1
    elsif x[j] < y[j]
        x.slice!(j)
        delCount += 1
    elsif x[j] > y[j]
        y.slice!(j)
        delCount += 1
    end
end
delCount += [x.length, y.length].max - [x.length, y.length].min
a = a[0,j]
b = b[0,j]
puts delCount