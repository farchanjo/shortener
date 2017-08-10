USE shortener;

INSERT INTO `users` (`created`, `modified`, `name`, `password`, `email`, `role`, `status`, `username`)
VALUES
  (now(), now(), 'Admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'farchanjo@gmail.com', 1,
   0, 'admin');