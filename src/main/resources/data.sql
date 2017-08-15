USE velociraptor;

INSERT INTO `users` (`created`, `modified`, `name`, `password`, `email`, `role`, `status`, `username`)
VALUES
  (now(), now(), 'Admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'farchanjo@gmail.com', 1,
   0, 'admin');

INSERT INTO velociraptor.domains (created, domain, modified, `ssl`, status, token)
VALUES ('2017-08-15 14:10:02.413000', 'sk.io', '2017-08-15 14:10:02.413000', FALSE, 0,
        '132ff788-539c-409d-ac02-11a06151eefe');