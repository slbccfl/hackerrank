#!/bin/ruby

a = 'cde'
b = 'abc'

a = gets.strip
b = gets.strip

a.sort!
b.sort!

delCount = 0
j = 0
while j <= [a.length, b.length].min do
    puts j + ' of ' + [a.length, b.length].min
    if a[j] != b[j]
        if a[j] < b[j]
            a.slice!(0)
            j -= 1
            delCount += 1
        elsif a[j] > b[j]
            b.pop
            j -= 1
            delCount += 1
        end
    end
end
a = a[0,i]
b = b[0,i]
puts delCount