INSERT INTO Users (LOGIN, PASSWORD) VALUES
	('Megan', '123');
INSERT INTO Users (LOGIN, PASSWORD) VALUES
	('Sam', '123');
INSERT INTO Users (LOGIN, PASSWORD) VALUES
	('Donald', '123');


INSERT INTO Articles (NAME, TOPIC, CONTENT, AUTHOR) VALUES
	('Some names', 'Time', 'Simple content', 1);
INSERT INTO Articles (NAME, TOPIC, CONTENT, AUTHOR) VALUES
	('some Cars', 'Time', 'Simple content', 2);
INSERT INTO Articles (NAME, TOPIC, CONTENT, AUTHOR) VALUES
	('an apple', 'Time', 'Simple content', 1);

INSERT INTO Comments (AUTHOR, ARTICLE, CONTENT) VALUES
	(3, 1, 'I am first');
INSERT INTO Comments (AUTHOR, ARTICLE, CONTENT) VALUES
	(3, 2, 'I am first');
INSERT INTO Comments (AUTHOR, ARTICLE, CONTENT) VALUES
	(3, 1, 'I am first and second');
