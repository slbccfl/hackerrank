#!/bin/ruby
require 'pry'

m,n = gets.strip.split(' ')
m = m.to_i
n = n.to_i
magazine = gets.strip
magazine = magazine.split(' ')
ransom = gets.strip
ransom = ransom.split(' ')

status = true
maghash = Hash.new
ranhash = Hash.new

if n > m
	status = false
else
	magazine.each do |word|
		if maghash[word] != nil
			maghash[word] += 1
		else
			maghash[word] = 1
		end
	end

	ransom.each do |word|
		if ranhash[word] != nil
			ranhash[word] += 1
		else
			ranhash[word] = 1
		end
	end

	ranhash.each do |word,count|
		if maghash[word] == nil 
			status = false
			break
		elsif maghash[word] < count 
			status = false
			break
		end
	end
end

if status
	puts "Yes"
else
	puts "No"
end