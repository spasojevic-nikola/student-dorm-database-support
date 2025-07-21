INSERT INTO Grad VALUES (1, 'Beograd', '11000', 1200000);
INSERT INTO Grad VALUES (2, 'Novi Sad', '21000', 350000);
INSERT INTO Grad VALUES (3, 'Nis', '18000', 260000);
INSERT INTO Grad VALUES (4, 'Kragujevac', '34000', 180000);
INSERT INTO Grad VALUES (5, 'Subotica', '24000', 100000);

INSERT INTO StudentskiDom VALUES (1, 'Dom Karaburma', 'Karaburmska 5', 400, 1);
INSERT INTO StudentskiDom VALUES (2, 'Dom Novi Sad', 'Bulevar Oslobođenja 40', 500, 2);
INSERT INTO StudentskiDom VALUES (3, 'Dom Nis', 'Centar bb', 300, 3);
INSERT INTO StudentskiDom VALUES (4, 'Dom KG', 'Ulica Branka Radicevica 12', 250, 4);
INSERT INTO StudentskiDom VALUES (5, 'Dom Subotica', 'Ulica 29. novembra 8', 200, 5);

INSERT INTO Soba VALUES (1, 2, 1, 1);
INSERT INTO Soba VALUES (2, 3, 2, 1);
INSERT INTO Soba VALUES (3, 2, 1, 2);
INSERT INTO Soba VALUES (4, 1, 3, 3);
INSERT INTO Soba VALUES (5, 2, 2, 4);

INSERT INTO Inventar VALUES (1, 2, 'Sto', 150.00, 'Dobro', 1);
INSERT INTO Inventar VALUES (2, 4, 'Stolica', 50.00, 'Novo', 1);
INSERT INTO Inventar VALUES (3, 1, 'Ormarić', 80.00, 'Polovno', 2);
INSERT INTO Inventar VALUES (4, 2, 'Lampa', 25.00, 'Dobro', 3);
INSERT INTO Inventar VALUES (5, 3, 'Krevet', 200.00, 'Novo', 4);

INSERT INTO Student VALUES (1, 'Marko', 'Markovic', 'M', '0611234567', 'Ulica A 10', 1);
INSERT INTO Student VALUES (2, 'Jelena', 'Jovanovic', 'Z', '0622345678', 'Ulica B 15', 2);
INSERT INTO Student VALUES (3, 'Nikola', 'Nikolic', 'M', '0633456789', 'Ulica C 20', 3);
INSERT INTO Student VALUES (4, 'Ana', 'Anic', 'Z', '0644567890', 'Ulica D 25', 4);
INSERT INTO Student VALUES (5, 'Petar', 'Petrovic', 'M', '0655678901', 'Ulica E 30', 5);

INSERT INTO Fakultet VALUES (1, 'ETF', 'Bulevar kralja Aleksandra 73', '0113212345', 'www.etf.bg.ac.rs');
INSERT INTO Fakultet VALUES (2, 'FTN', 'Trg Dositeja Obradovica 6', '0214723456', 'www.ftn.uns.ac.rs');
INSERT INTO Fakultet VALUES (3, 'PMF Nis', 'Visegradska 33', '018514321', 'www.pmf.ni.ac.rs');
INSERT INTO Fakultet VALUES (4, 'FPN', 'Jove Ilica 165', '0113092345', 'www.fpn.bg.ac.rs');
INSERT INTO Fakultet VALUES (5, 'Ekonomski', 'Kamenicka 6', '0112620345', 'www.ekof.bg.ac.rs');

INSERT INTO Studira VALUES (1, 1);
INSERT INTO Studira VALUES (2, 2);
INSERT INTO Studira VALUES (3, 3);
INSERT INTO Studira VALUES (4, 4);
INSERT INTO Studira VALUES (5, 5);

INSERT INTO Placanje VALUES (1, 12000.00, 'Kirija', TO_DATE('01-09-2025', 'DD-MM-YYYY'), 'Septembar', 1);
INSERT INTO Placanje VALUES (2, 14000.00, 'Kirija2', TO_DATE('02-09-2025', 'DD-MM-YYYY'), 'Septembar', 2);
INSERT INTO Placanje VALUES (3, 15000.00, 'Kirija3', TO_DATE('03-09-2025', 'DD-MM-YYYY'), 'Septembar', 3);
INSERT INTO Placanje VALUES (4, 16000.00, 'Kirija4', TO_DATE('04-09-2025', 'DD-MM-YYYY'), 'Septembar', 4);
INSERT INTO Placanje VALUES (5, 18000.00, 'Kirija5', TO_DATE('05-09-2025', 'DD-MM-YYYY'), 'Septembar', 5);

INSERT INTO Zaposleni VALUES (1, 'Ivana', 'Ivic', TO_DATE('15-03-1980', 'DD-MM-YYYY'), '0601234567', 'Domar', 1);
INSERT INTO Zaposleni VALUES (2, 'Milos', 'Milic', TO_DATE('20-07-1975', 'DD-MM-YYYY'), '0602345678', 'Domar', 2);
INSERT INTO Zaposleni VALUES (3, 'Sara', 'Saranovic', TO_DATE('01-05-1990', 'DD-MM-YYYY'), '0603456789', 'Cistacica', 1);
INSERT INTO Zaposleni VALUES (4, 'Luka', 'Lukic', TO_DATE('25-12-1985', 'DD-MM-YYYY'), '0604567890', 'Upravnik', 3);
INSERT INTO Zaposleni VALUES (5, 'Mira', 'Miric', TO_DATE('18-09-1982', 'DD-MM-YYYY'), '0605678901', 'Cistacica', 4);

INSERT INTO Domar VALUES (1);
INSERT INTO Domar VALUES (2);

INSERT INTO Kvar VALUES (1, 'Otvoren', 'Lampa pokvarena', 'Visok', 1, 1);
INSERT INTO Kvar VALUES (2, 'U obradi', 'Kvar na bojleru', 'Srednji', 2, 2);
INSERT INTO Kvar VALUES (3, 'Zatvoren', 'Vrata se ne zatvaraju', 'Nizak', 3, 1);
INSERT INTO Kvar VALUES (4, 'Otvoren', 'Grejanje ne radi', 'Visok', 4, 2);
INSERT INTO Kvar VALUES (5, 'U obradi', 'Prozor se ne otvara', 'Srednji', 5, 1);

INSERT INTO Upravnik VALUES (4);

INSERT INTO Kartica VALUES (1, TO_DATE('01-09-2025', 'DD-MM-YYYY'), TO_DATE('30-09-2025', 'DD-MM-YYYY'), 'Ulaz', 4, 1);
INSERT INTO Kartica VALUES (2, TO_DATE('02-09-2025', 'DD-MM-YYYY'), TO_DATE('30-09-2025', 'DD-MM-YYYY'), 'Ulaz', 4, 2);
INSERT INTO Kartica VALUES (3, TO_DATE('03-09-2025', 'DD-MM-YYYY'), TO_DATE('30-09-2025', 'DD-MM-YYYY'), 'Ulaz', 4, 3);
INSERT INTO Kartica VALUES (4, TO_DATE('04-09-2025', 'DD-MM-YYYY'), TO_DATE('30-09-2025', 'DD-MM-YYYY'), 'Ulaz', 4, 4);
INSERT INTO Kartica VALUES (5, TO_DATE('05-09-2025', 'DD-MM-YYYY'), TO_DATE('30-09-2025', 'DD-MM-YYYY'), 'Ulaz', 4, 5);

INSERT INTO Cistacica VALUES (3, NULL);
INSERT INTO Cistacica VALUES (5, 3);

INSERT INTO CISCENJE VALUES (3, TO_DATE('10-09-2025', 'DD-MM-YYYY'), 1);
INSERT INTO CISCENJE VALUES (3, TO_DATE('11-09-2025', 'DD-MM-YYYY'), 2);
INSERT INTO CISCENJE VALUES (5, TO_DATE('10-09-2025', 'DD-MM-YYYY'), 3);
INSERT INTO CISCENJE VALUES (5, TO_DATE('11-09-2025', 'DD-MM-YYYY'), 4);
INSERT INTO CISCENJE VALUES (5, TO_DATE('12-09-2025', 'DD-MM-YYYY'), 5);