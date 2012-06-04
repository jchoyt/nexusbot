--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: factions; Type: TABLE; Schema: public; Owner: jchoyt; Tablespace: 
--

CREATE TABLE factions (
    faction character varying(200) NOT NULL,
    level integer,
    karma integer,
    members integer,
    honor integer,
    alignment character varying(10),
    scouted timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.factions OWNER TO jchoyt;

--
-- Name: sh; Type: TABLE; Schema: public; Owner: jchoyt; Tablespace: 
--

CREATE TABLE sh (
    faction character varying(200) NOT NULL,
    plane character varying(50),
    x integer,
    y integer,
    scouted timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.sh OWNER TO jchoyt;

--
-- Name: hg; Type: VIEW; Schema: public; Owner: jchoyt
--

CREATE VIEW hg AS
    SELECT s.faction, f.level, f.karma, f.honor, f.members, f.alignment, s.plane, s.x, s.y FROM factions f, sh s WHERE ((f.faction)::text = (s.faction)::text);


ALTER TABLE public.hg OWNER TO jchoyt;

--
-- Data for Name: factions; Type: TABLE DATA; Schema: public; Owner: jchoyt
--

INSERT INTO factions VALUES ('Novus Ordo', 10, 250, 10, 0, 'Good', '2009-05-29 16:57:35.874086');
INSERT INTO factions VALUES ('Satan''s Workshop', 10, 255, 11, 0, 'Unaligned', '2009-05-29 16:57:36.01425');
INSERT INTO factions VALUES ('Team Laser Explosion', 10, 40, 13, 68, 'Unaligned', '2009-05-29 16:57:36.026253');
INSERT INTO factions VALUES ('TEAM TAUNT', 10, 12, 10, 120, 'Evil', '2009-05-29 16:57:36.042427');
INSERT INTO factions VALUES ('The Bermuda Club International', 10, 185, 9, 0, 'Good', '2009-05-29 16:57:37.28826');
INSERT INTO factions VALUES ('The Brotherhood', 10, 5, 7, 93, 'Good', '2009-05-29 16:57:40.302055');
INSERT INTO factions VALUES ('The Fat Duck', 10, 5, 7, 0, 'Evil', '2009-05-29 16:57:42.329696');
INSERT INTO factions VALUES ('The Feathered Foe', 10, 6, 9, 11, 'Good', '2009-05-29 16:57:44.337983');
INSERT INTO factions VALUES ('The Fighting Jesi', 10, 240, 8, 0, 'Good', '2009-05-29 16:57:46.347392');
INSERT INTO factions VALUES ('The Necrotic Carnival', 10, 18, 12, 62, 'Evil', '2009-05-29 16:57:48.360532');
INSERT INTO factions VALUES ('The Rambling Drunks', 10, 534, 11, 0, 'Evil', '2009-05-29 16:57:50.369787');
INSERT INTO factions VALUES ('The Robot Mafia', 10, 11, 11, 0, 'Evil', '2009-05-29 16:57:52.387046');
INSERT INTO factions VALUES ('The Salty Dogs', 10, 40, 8, 16, 'Evil', '2009-05-29 16:57:54.398308');
INSERT INTO factions VALUES ('Transmigrated Afterlife Party', 10, 55, 13, 1, 'Evil', '2009-05-29 16:57:56.414036');
INSERT INTO factions VALUES ('Undeadites', 10, 295, 9, 136, 'Evil', '2009-05-29 16:57:59.442775');
INSERT INTO factions VALUES ('Alcoholics Anonymous', 9, 6, 6, 11, 'Evil', '2009-05-29 16:58:01.450033');
INSERT INTO factions VALUES ('Clothed with Authority', 9, 66, 5, 190, 'Good', '2009-05-29 16:58:05.48211');
INSERT INTO factions VALUES ('Cult of Crabs', 9, 16, 9, 20, 'Evil', '2009-05-29 16:58:07.493884');
INSERT INTO factions VALUES ('Dark Horde', 9, 55, 9, 1, 'Evil', '2009-05-29 16:58:09.509145');
INSERT INTO factions VALUES ('Deadland Outlaws', 9, 94, 6, 38, 'Evil', '2009-05-29 16:58:11.522969');
INSERT INTO factions VALUES ('Death''s Soul Collectors', 9, 42, 5, 36, 'Evil', '2009-05-29 16:58:13.547257');
INSERT INTO factions VALUES ('Demons without Borders', 9, 40, 8, 56, 'Evil', '2009-05-29 16:58:15.562006');
INSERT INTO factions VALUES ('Doctors Without Borders', 9, 162, 8, 0, 'Good', '2009-05-29 16:58:17.575064');
INSERT INTO factions VALUES ('Doom Warders', 9, 6, 4, 92, 'Evil', '2009-05-29 16:58:19.587018');
INSERT INTO factions VALUES ('Extra Special Super Awesome', 9, 40, 4, 0, 'Good', '2009-05-29 16:58:21.599014');
INSERT INTO factions VALUES ('Lithium', 9, 6, 5, 34, 'Unaligned', '2009-05-29 16:58:23.619111');
INSERT INTO factions VALUES ('Maxximum Overdrive', 9, 102, 6, 24, 'Good', '2009-05-29 16:58:25.634961');
INSERT INTO factions VALUES ('Mickie Mouse Whore House', 9, 6, 13, 5, 'Evil', '2009-05-29 16:58:27.156046');
INSERT INTO factions VALUES ('Nothing Else Matters', 9, 24, 7, 53, 'Good', '2009-05-29 16:58:29.15915');
INSERT INTO factions VALUES ('NRA Nutjobs', 9, 84, 8, 0, 'Good', '2009-05-29 16:58:31.177761');
INSERT INTO factions VALUES ('Privateers of the Elder Sign', 9, 5, 6, 27, 'Good', '2009-05-29 16:58:35.213113');
INSERT INTO factions VALUES ('Random Shocking Violence', 9, 108, 7, 0, 'Evil', '2009-05-29 16:58:37.234006');
INSERT INTO factions VALUES ('Seekers of the Void', 9, 5, 15, 0, 'Evil', '2009-05-29 16:58:39.250013');
INSERT INTO factions VALUES ('Sentinels', 9, 30, 6, 2, 'Good', '2009-05-29 16:58:41.269242');
INSERT INTO factions VALUES ('Serra''s Sanctum', 9, 12, 7, 116, 'Good', '2009-05-29 16:58:43.282019');
INSERT INTO factions VALUES ('Servants of the Void', 9, 25, 8, 42, 'Evil', '2009-05-29 16:58:45.304316');
INSERT INTO factions VALUES ('Silverado Isolationists', 9, 5, 5, 20, 'Evil', '2009-05-29 16:58:47.314847');
INSERT INTO factions VALUES ('Status Quo', 9, 30, 7, 0, 'Unaligned', '2009-05-29 16:58:49.331018');
INSERT INTO factions VALUES ('Suburban Ed''s Peasant Militia', 9, 300, 6, 0, 'Good', '2009-05-29 16:58:51.351482');
INSERT INTO factions VALUES ('The Abandoned', 9, 40, 7, 0, 'Good', '2009-05-29 16:58:53.370198');
INSERT INTO factions VALUES ('The Badge Whores', 9, 72, 4, 0, 'Evil', '2009-05-29 16:59:43.475153');
INSERT INTO factions VALUES ('The Care Bears', 9, 165, 4, 1, 'Good', '2009-05-29 16:59:43.482139');
INSERT INTO factions VALUES ('The City Watch', 9, 29, 5, 0, 'Good', '2009-05-29 16:59:43.489482');
INSERT INTO factions VALUES ('The Devil''s Coin Regulars', 9, 5, 7, 0, 'Evil', '2009-05-29 16:59:43.498324');
INSERT INTO factions VALUES ('The Divine Army', 9, 757, 6, 7, 'Good', '2009-05-29 16:59:43.505162');
INSERT INTO factions VALUES ('The Evil League of Evil', 9, 70, 5, 54, 'Evil', '2009-05-29 16:59:43.797555');
INSERT INTO factions VALUES ('The Friends of English Magic', 9, 211, 6, 0, 'Unaligned', '2009-05-29 16:59:45.42161');
INSERT INTO factions VALUES ('The Grey Fedora Gang', 9, 48, 6, 0, 'Unaligned', '2009-05-29 16:59:48.438986');
INSERT INTO factions VALUES ('The Holy See', 9, 295, 5, 0, 'Good', '2009-05-29 16:59:50.449703');
INSERT INTO factions VALUES ('The Infernal Horde', 9, 368, 9, 5, 'Evil', '2009-05-29 16:59:52.465615');
INSERT INTO factions VALUES ('The Jedi Order', 9, 15, 9, 0, 'Good', '2009-05-29 16:59:54.469841');
INSERT INTO factions VALUES ('The League of Science-Heroes', 9, 175, 10, 28, 'Good', '2009-05-29 16:59:56.493711');
INSERT INTO factions VALUES ('The Private Penthouse', 9, 21, 7, 150, 'Good', '2009-05-29 16:59:58.537513');
INSERT INTO factions VALUES ('The Sith Order', 9, 12, 5, 0, 'Evil', '2009-05-29 17:00:00.546763');
INSERT INTO factions VALUES ('The Skyfalling Eighth', 9, 20, 10, 0, 'Evil', '2009-05-29 17:00:02.933781');
INSERT INTO factions VALUES ('The Stain Gang', 9, 185, 4, 5, 'Evil', '2009-05-29 17:00:06.589904');
INSERT INTO factions VALUES ('The Three Kings', 9, 11, 4, 0, 'Unaligned', '2009-05-29 17:00:08.600467');
INSERT INTO factions VALUES ('The Vengeful Wrath of Hashaa', 9, 6, 4, 209, 'Unaligned', '2009-05-29 17:00:10.622205');
INSERT INTO factions VALUES ('The Watchers', 9, 60, 6, 0, 'Evil', '2009-05-29 17:00:13.634949');
INSERT INTO factions VALUES ('Trinitas', 9, 80, 5, 23, 'Good', '2009-05-29 17:00:15.169353');
INSERT INTO factions VALUES ('Udrangor''s Shooting Range', 9, 12, 6, 0, 'Evil', '2009-05-29 17:00:17.187149');
INSERT INTO factions VALUES ('Void Theory', 9, 0, 4, 605, 'Evil', '2009-05-29 17:00:19.214186');
INSERT INTO factions VALUES ('Wolf''s Dragoons', 9, 20, 6, 58, 'Good', '2009-05-29 17:00:21.234078');
INSERT INTO factions VALUES ('Ye Olde Component Shoppe', 9, 40, 8, 0, 'Unaligned', '2009-05-29 17:00:23.251098');
INSERT INTO factions VALUES ('A Confederacy of Douchebags', 8, 5, 7, 60, 'Evil', '2009-05-29 17:00:25.267258');
INSERT INTO factions VALUES ('Apocalypse Now!', 8, 10, 4, 0, 'Good', '2009-05-29 17:00:27.28593');
INSERT INTO factions VALUES ('Assamites', 8, 5, 5, 0, 'Good', '2009-05-29 17:00:29.297113');
INSERT INTO factions VALUES ('Axes High', 8, 17, 8, 0, 'Unaligned', '2009-05-29 17:00:31.313212');
INSERT INTO factions VALUES ('Black Goat With 1000 Young', 8, 55, 5, 0, 'Evil', '2009-05-29 17:00:35.341877');
INSERT INTO factions VALUES ('Blighty', 8, 150, 4, 123, 'Good', '2009-05-29 17:00:37.350708');
INSERT INTO factions VALUES ('Church of the Resurrection', 8, 120, 5, 0, 'Good', '2009-05-29 17:00:39.370505');
INSERT INTO factions VALUES ('Dol Guldur', 8, 25, 4, 8, 'Evil', '2009-05-29 17:00:41.386374');
INSERT INTO factions VALUES ('Evil Warriors of Eternia', 8, 84, 5, 94, 'Evil', '2009-05-29 17:00:43.403026');
INSERT INTO factions VALUES ('Ex Oblivione', 8, 10, 9, 28, 'Good', '2009-05-29 17:00:45.417332');
INSERT INTO factions VALUES ('Arcadia of My Youth', 6, 96, 1, 0, 'Good', '2009-05-29 17:08:17.284632');
INSERT INTO factions VALUES ('Army of Darkness', 6, 41, 1, 47, 'Evil', '2009-05-29 17:08:17.297369');
INSERT INTO factions VALUES ('Atalanta''s Armory', 6, 96, 1, 0, 'Good', '2009-05-29 17:08:17.303642');
INSERT INTO factions VALUES ('Backalley Pharmacists', 6, 185, 1, 0, 'Good', '2009-05-29 17:08:17.308024');
INSERT INTO factions VALUES ('Bazaar of the Bizarre', 6, 5, 2, 0, 'Good', '2009-05-29 17:08:17.315131');
INSERT INTO factions VALUES ('Black Sox', 6, 5, 3, 43, 'Good', '2009-05-29 17:08:18.30751');
INSERT INTO factions VALUES ('Brigade', 6, 0, 1, 13, 'Unaligned', '2009-05-29 17:08:19.350124');
INSERT INTO factions VALUES ('CANADA', 6, 5, 3, 0, 'Good', '2009-05-29 17:08:21.425213');
INSERT INTO factions VALUES ('Cheers', 6, 0, 1, 0, 'Evil', '2009-05-29 17:08:23.383337');
INSERT INTO factions VALUES ('Children of Sekhmet', 6, 10, 2, 0, 'Good', '2009-05-29 17:08:25.393515');
INSERT INTO factions VALUES ('Cool Guy Crew', 6, 5, 1, 0, 'Good', '2009-05-29 17:08:27.867003');
INSERT INTO factions VALUES ('Creeping Darkness', 6, 0, 1, 0, 'Evil', '2009-05-29 17:08:30.529227');
INSERT INTO factions VALUES ('Cult of Hekate', 6, 0, 0, 34, 'Good', '2009-05-29 17:08:32.473936');
INSERT INTO factions VALUES ('Damage, Inc.', 6, 102, 1, 21, 'Good', '2009-05-29 17:08:34.520906');
INSERT INTO factions VALUES ('Doodles', 6, 5, 2, 0, 'Evil', '2009-05-29 17:08:36.568783');
INSERT INTO factions VALUES ('double 6', 6, 6, 1, 25, 'Good', '2009-05-29 17:08:39.500413');
INSERT INTO factions VALUES ('Drunken Doctors', 6, 0, 1, 13, 'Unaligned', '2009-05-29 17:08:41.560159');
INSERT INTO factions VALUES ('Dwarf Fortress', 6, 5, 1, 0, 'Good', '2009-05-29 17:08:43.52789');
INSERT INTO factions VALUES ('Endangered Species', 6, 82, 2, 0, 'Good', '2009-05-29 17:08:45.542178');
INSERT INTO factions VALUES ('Entropy', 6, 12, 0, 0, 'Evil', '2009-05-29 17:08:47.553853');
INSERT INTO factions VALUES ('Extemporaneous', 6, 0, 2, 363, 'Evil', '2009-05-29 17:08:49.56485');
INSERT INTO factions VALUES ('Forgotten City Cleaning, Inc.', 6, 0, 1, 28, 'Good', '2009-05-29 17:08:51.574665');
INSERT INTO factions VALUES ('Gate of Babylon', 6, 10, 1, 0, 'Good', '2009-05-29 17:08:53.585846');
INSERT INTO factions VALUES ('Generic Good Faction', 6, 5, 1, 0, 'Good', '2009-05-29 17:08:55.60407');
INSERT INTO factions VALUES ('God Inc.', 6, 36, 1, 38, 'Good', '2009-05-29 17:08:57.699875');
INSERT INTO factions VALUES ('Grizzled Geezer', 6, 25, 2, 0, 'Good', '2009-05-29 17:08:59.152695');
INSERT INTO factions VALUES ('Hermitage', 6, 10, 1, 0, 'Good', '2009-05-29 17:09:01.509456');
INSERT INTO factions VALUES ('Falkenberg''s Legion', 8, 12, 4, 152, 'Unaligned', '2009-05-29 17:00:47.43562');
INSERT INTO factions VALUES ('Funky Town', 8, 42, 3, 173, 'Good', '2009-05-29 17:00:49.456032');
INSERT INTO factions VALUES ('Guardian', 8, 90, 5, 47, 'Good', '2009-05-29 17:00:51.467445');
INSERT INTO factions VALUES ('Holy Dark', 8, 70, 3, 0, 'Good', '2009-05-29 17:00:53.478781');
INSERT INTO factions VALUES ('Kick Push', 8, 0, 4, 0, 'Evil', '2009-05-29 17:00:55.494972');
INSERT INTO factions VALUES ('NADA', 8, 6, 3, 0, 'Good', '2009-05-29 17:00:57.512003');
INSERT INTO factions VALUES ('Acta Sanctorum', 14, 1050, 68, 49, 'Good', '2009-05-29 16:51:04.635088');
INSERT INTO factions VALUES ('Freemasons', 14, 54, 93, 53, 'Good', '2009-05-29 16:51:04.647385');
INSERT INTO factions VALUES ('Ridleytown Resistance Front', 14, 306, 81, 51, 'Unaligned', '2009-05-29 16:51:04.659382');
INSERT INTO factions VALUES ('Sanitarium', 14, 465, 70, 344, 'Evil', '2009-05-29 16:51:06.6429');
INSERT INTO factions VALUES ('The Divine Slayers', 14, 761, 76, 0, 'Good', '2009-05-29 16:51:08.247129');
INSERT INTO factions VALUES ('Alliance of St. Germaine', 13, 166, 60, 45, 'Good', '2009-05-29 16:51:10.263174');
INSERT INTO factions VALUES ('Balance', 13, 536, 41, 0, 'Unaligned', '2009-05-29 16:51:12.283143');
INSERT INTO factions VALUES ('Faber Castell Free Rolling Ink', 13, 11, 35, 536, 'Evil', '2009-05-29 16:51:14.303278');
INSERT INTO factions VALUES ('The Demons Next Door', 13, 120, 43, 30, 'Evil', '2009-05-29 16:51:17.315276');
INSERT INTO factions VALUES ('The Scourge', 13, 566, 34, 94, 'Evil', '2009-05-29 16:51:19.331149');
INSERT INTO factions VALUES ('All Your Souls', 12, 316, 29, 133, 'Good', '2009-05-29 16:51:21.351387');
INSERT INTO factions VALUES ('Assassins of the Myst', 12, 105, 28, 78, 'Unaligned', '2009-05-29 16:51:23.359267');
INSERT INTO factions VALUES ('Celestial Defense Force', 12, 485, 20, 77, 'Good', '2009-05-29 16:51:25.375401');
INSERT INTO factions VALUES ('CHAOS CHAOS CHAOS', 12, 18, 37, 67, 'Evil', '2009-05-29 16:51:27.398104');
INSERT INTO factions VALUES ('Children of the Lightbulb', 12, 210, 35, 19, 'Good', '2009-05-29 16:51:31.427374');
INSERT INTO factions VALUES ('Convent of Nuns', 12, 140, 23, 19, 'Good', '2009-05-29 16:51:33.439219');
INSERT INTO factions VALUES ('Crypt Lords of Hashaa', 12, 114, 19, 19, 'Unaligned', '2009-05-29 16:51:35.459362');
INSERT INTO factions VALUES ('Edgetown Original Gangsters', 12, 395, 27, 0, 'Unaligned', '2009-05-29 16:51:37.467081');
INSERT INTO factions VALUES ('Forsaken', 12, 108, 24, 146, 'Unaligned', '2009-05-29 16:51:40.487307');
INSERT INTO factions VALUES ('Pirates of R''lyeh', 15, 278, 116, 31, 'Evil', '2009-05-29 16:51:42.511345');
INSERT INTO factions VALUES ('Heart of Oak', 12, 102, 17, 42, 'Good', '2009-05-29 16:52:45.586201');
INSERT INTO factions VALUES ('Imperium Romanum', 12, 198, 23, 17, 'Good', '2009-05-29 16:52:45.598049');
INSERT INTO factions VALUES ('Invisible Danger', 12, 205, 18, 102, 'Good', '2009-05-29 16:52:45.610278');
INSERT INTO factions VALUES ('Knights of Arcadia', 12, 386, 21, 0, 'Good', '2009-05-29 16:52:45.626283');
INSERT INTO factions VALUES ('Lawful Good!!', 12, 230, 22, 54, 'Good', '2009-05-29 16:52:45.637996');
INSERT INTO factions VALUES ('Newbie Welcome Wagon', 12, 5, 70, 0, 'Good', '2009-05-29 16:52:46.3081');
INSERT INTO factions VALUES ('Nordic Avengers', 12, 126, 18, 3, 'Evil', '2009-05-29 16:52:47.373924');
INSERT INTO factions VALUES ('Operation Opposition', 12, 90, 29, 211, 'Evil', '2009-05-29 16:52:49.729895');
INSERT INTO factions VALUES ('Shadow', 12, 114, 22, 57, 'Evil', '2009-05-29 16:52:51.729851');
INSERT INTO factions VALUES ('The Dark Oppressors Guild', 12, 260, 22, 0, 'Evil', '2009-05-29 16:52:53.458092');
INSERT INTO factions VALUES ('The Faithful', 12, 485, 23, 5, 'Good', '2009-05-29 16:52:55.721873');
INSERT INTO factions VALUES ('The Legion', 12, 250, 28, 131, 'Unaligned', '2009-05-29 16:52:57.874196');
INSERT INTO factions VALUES ('The Phoenix', 12, 610, 28, 47, 'Unaligned', '2009-05-29 16:53:01.78533');
INSERT INTO factions VALUES ('The Vanir', 12, 170, 29, 19, 'Good', '2009-05-29 16:53:03.658109');
INSERT INTO factions VALUES ('Busty''s Littlest Whorehouse', 11, 114, 11, 40, 'Good', '2009-05-29 16:53:05.374069');
INSERT INTO factions VALUES ('Crystalmir Corporation', 11, 474, 16, 79, 'Good', '2009-05-29 16:53:08.390051');
INSERT INTO factions VALUES ('Dunell Hills Police Department', 11, 5, 13, 0, 'Good', '2009-05-29 16:53:10.411116');
INSERT INTO factions VALUES ('Fervant Apathy', 11, 276, 16, 74, 'Evil', '2009-05-29 16:53:12.418105');
INSERT INTO factions VALUES ('Friends of BK', 11, 156, 11, 29, 'Good', '2009-05-29 16:53:14.432176');
INSERT INTO factions VALUES ('Friends of the Wrench', 11, 336, 16, 62, 'Good', '2009-05-29 16:53:16.44304');
INSERT INTO factions VALUES ('Glimmering Crusaders', 11, 55, 15, 1, 'Good', '2009-05-29 16:53:18.461361');
INSERT INTO factions VALUES ('Ladies who Lunch', 11, 190, 16, 0, 'Good', '2009-05-29 16:53:20.47773');
INSERT INTO factions VALUES ('Legion of Error', 11, 370, 19, 16, 'Good', '2009-05-29 16:53:22.541646');
INSERT INTO factions VALUES ('Magi Anonymous', 11, 168, 19, 0, 'Good', '2009-05-29 16:53:24.239407');
INSERT INTO factions VALUES ('Murder Inc.', 11, 162, 15, 0, 'Evil', '2009-05-29 16:53:26.137954');
INSERT INTO factions VALUES ('Ordo Malleus', 11, 408, 17, 84, 'Unaligned', '2009-05-29 16:53:28.266411');
INSERT INTO factions VALUES ('Serious Business', 11, 245, 13, 116, 'Evil', '2009-05-29 16:53:32.29115');
INSERT INTO factions VALUES ('Soldiers of Duat', 11, 84, 13, 36, 'Evil', '2009-05-29 16:53:34.309826');
INSERT INTO factions VALUES ('The Army of the Way', 11, 15, 15, 45, 'Good', '2009-05-29 16:53:36.33001');
INSERT INTO factions VALUES ('The Black Mambas', 11, 36, 17, 0, 'Unaligned', '2009-05-29 16:53:38.342044');
INSERT INTO factions VALUES ('The Claw', 11, 145, 19, 30, 'Evil', '2009-05-29 16:53:40.354146');
INSERT INTO factions VALUES ('The Council', 11, 96, 15, 32, 'Good', '2009-05-29 16:53:42.374011');
INSERT INTO factions VALUES ('The Coven', 11, 225, 21, 53, 'Unaligned', '2009-05-29 16:53:44.390476');
INSERT INTO factions VALUES ('The High Knights of Liberation', 11, 265, 14, 0, 'Good', '2009-05-29 16:53:46.410341');
INSERT INTO factions VALUES ('The Sewer Rats', 11, 90, 15, 45, 'Unaligned', '2009-05-29 16:53:49.434307');
INSERT INTO factions VALUES ('The Silent Order', 11, 85, 13, 27, 'Good', '2009-05-29 16:53:51.457976');
INSERT INTO factions VALUES ('Unseen University', 11, 111, 15, 10, 'Good', '2009-05-29 16:53:53.461848');
INSERT INTO factions VALUES ('Warriors', 11, 11, 15, 22, 'Good', '2009-05-29 16:53:55.486073');
INSERT INTO factions VALUES ('Who Made Who', 11, 45, 13, 385, 'Evil', '2009-05-29 16:53:57.517352');
INSERT INTO factions VALUES ('Zpies', 11, 264, 13, 19, 'Unaligned', '2009-05-29 16:53:59.522515');
INSERT INTO factions VALUES ('Angels with Dirty Faces', 10, 263, 8, 5, 'Good', '2009-05-29 16:54:03.546047');
INSERT INTO factions VALUES ('Blacklight', 10, 7, 9, 0, 'Good', '2009-05-29 16:54:05.593071');
INSERT INTO factions VALUES ('Brutal Deluxe', 10, 35, 8, 174, 'Good', '2009-05-29 16:54:07.578198');
INSERT INTO factions VALUES ('Paradise is for Good, Suckas!', 8, 0, 3, 0, 'Good', '2009-05-29 17:00:59.530737');
INSERT INTO factions VALUES ('Rogue''s Guild', 8, 54, 3, 11, 'Good', '2009-05-29 17:01:01.81021');
INSERT INTO factions VALUES ('Serenity', 8, 35, 3, 0, 'Good', '2009-05-29 17:01:05.578677');
INSERT INTO factions VALUES ('Thari Squadron', 8, 60, 3, 0, 'Evil', '2009-05-29 17:01:07.599152');
INSERT INTO factions VALUES ('The Alchemist''s Guild', 8, 45, 4, 0, 'Good', '2009-05-29 17:01:09.605209');
INSERT INTO factions VALUES ('The Atheist Regime', 8, 167, 4, 0, 'Unaligned', '2009-05-29 17:01:11.620692');
INSERT INTO factions VALUES ('The Hellbound', 8, 95, 3, 21, 'Evil', '2009-05-29 17:01:13.138447');
INSERT INTO factions VALUES ('The Hermitage', 8, 42, 5, 0, 'Evil', '2009-05-29 17:01:15.157633');
INSERT INTO factions VALUES ('The Justice League', 8, 40, 5, 32, 'Good', '2009-05-29 17:01:17.172922');
INSERT INTO factions VALUES ('The Kakistrocratic Genocide', 8, 115, 4, 23, 'Evil', '2009-05-29 17:01:19.205831');
INSERT INTO factions VALUES ('The Knights Templar', 8, 20, 3, 0, 'Good', '2009-05-29 17:01:21.213835');
INSERT INTO factions VALUES ('The Morgue', 8, 10, 8, 40, 'Evil', '2009-05-29 17:01:23.230545');
INSERT INTO factions VALUES ('The Searchers', 8, 45, 3, 0, 'Good', '2009-05-29 17:01:25.249615');
INSERT INTO factions VALUES ('The Undecided Order', 8, 15, 5, 0, 'Good', '2009-05-29 17:01:27.269834');
INSERT INTO factions VALUES ('Total Annihilation', 8, 0, 3, 6, 'Unaligned', '2009-05-29 17:01:29.286283');
INSERT INTO factions VALUES ('Wright''s Corridor Free Clinic', 8, 223, 3, 0, 'Good', '2009-05-29 17:01:31.30304');
INSERT INTO factions VALUES ('Z Squad', 8, 12, 4, 0, 'Unaligned', '2009-05-29 17:01:33.322699');
INSERT INTO factions VALUES ('ö wants a 1400 HP ward', 8, 0, 3, 27, 'Evil', '2009-05-29 17:01:37.354628');
INSERT INTO factions VALUES ('Blood Hawk Brigade', 7, 0, 3, 0, 'Good', '2009-05-29 17:01:39.375371');
INSERT INTO factions VALUES ('Clan Hellstrom', 7, 10, 3, 0, 'Evil', '2009-05-29 17:01:41.380558');
INSERT INTO factions VALUES ('Damned Librarians', 7, 33, 2, 0, 'Evil', '2009-05-29 17:03:55.175267');
INSERT INTO factions VALUES ('Den Of Bunnies', 7, 260, 3, 0, 'Good', '2009-05-29 17:03:55.182343');
INSERT INTO factions VALUES ('Failraiders of the Lost Ark', 7, 10, 3, 214, 'Good', '2009-05-29 17:03:55.201167');
INSERT INTO factions VALUES ('Getsmart', 7, 11, 2, 0, 'Good', '2009-05-29 17:03:55.208714');
INSERT INTO factions VALUES ('GMT Breakfast Club', 7, 12, 2, 0, 'Unaligned', '2009-05-29 17:03:55.217324');
INSERT INTO factions VALUES ('Hit me', 7, 5, 3, 0, 'Good', '2009-05-29 17:03:56.40445');
INSERT INTO factions VALUES ('Just Leave Me Alone', 7, 27, 3, 0, 'Unaligned', '2009-05-29 17:03:58.312589');
INSERT INTO factions VALUES ('Lack Confederacy', 7, 25, 5, 0, 'Good', '2009-05-29 17:03:59.639581');
INSERT INTO factions VALUES ('LOLCATS', 7, 5, 3, 0, 'Evil', '2009-05-29 17:04:01.15483');
INSERT INTO factions VALUES ('Long Distance Ghosts', 7, 30, 2, 0, 'Evil', '2009-05-29 17:04:03.187112');
INSERT INTO factions VALUES ('M.A.S.K.', 7, 81, 2, 0, 'Good', '2009-05-29 17:04:07.216573');
INSERT INTO factions VALUES ('Clan McCrea', 10, 168, 9, 0, 'Evil', '2009-05-29 16:54:09.599138');
INSERT INTO factions VALUES ('Coven of the Blood Moon', 10, 60, 9, 0, 'Good', '2009-05-29 16:54:11.613961');
INSERT INTO factions VALUES ('Covert Moles', 10, 205, 9, 0, 'Good', '2009-05-29 16:54:13.634264');
INSERT INTO factions VALUES ('Divine Emergency Management', 10, 66, 13, 82, 'Good', '2009-05-29 16:54:15.145878');
INSERT INTO factions VALUES ('Evil Engineers, LLC', 10, 106, 11, 19, 'Evil', '2009-05-29 16:54:17.166024');
INSERT INTO factions VALUES ('Fermentation Inc.', 10, 106, 9, 20, 'Good', '2009-05-29 16:54:19.186091');
INSERT INTO factions VALUES ('Fight Club', 10, 216, 8, 0, 'Evil', '2009-05-29 16:54:21.206068');
INSERT INTO factions VALUES ('Good Dogs', 10, 315, 8, 87, 'Good', '2009-05-29 16:54:23.223316');
INSERT INTO factions VALUES ('Grey Knights', 10, 110, 8, 11, 'Good', '2009-05-29 16:54:25.22918');
INSERT INTO factions VALUES ('Guild of the DragonMages', 10, 144, 9, 9, 'Unaligned', '2009-05-29 16:54:27.253968');
INSERT INTO factions VALUES ('Haunters of the Dark', 10, 24, 9, 0, 'Evil', '2009-05-29 16:54:29.270007');
INSERT INTO factions VALUES ('Hentai All-Stars', 10, 300, 7, 38, 'Evil', '2009-05-29 16:54:33.341629');
INSERT INTO factions VALUES ('Heroes of the Lance', 10, 150, 7, 0, 'Good', '2009-05-29 16:54:35.317661');
INSERT INTO factions VALUES ('Kommando Dosenbier', 10, 84, 11, 0, 'Evil', '2009-05-29 16:54:37.333637');
INSERT INTO factions VALUES ('Militant Order of Barhah', 10, 24, 10, 41, 'Evil', '2009-05-29 16:54:39.35801');
INSERT INTO factions VALUES ('Nakamura Technologies', 10, 65, 9, 92, 'Evil', '2009-05-29 16:54:41.369381');
INSERT INTO factions VALUES ('Necroscope', 10, 50, 11, 33, 'Evil', '2009-05-29 16:54:43.393714');
INSERT INTO factions VALUES ('MinipaxII', 7, 5, 2, 0, 'Good', '2009-05-29 17:04:09.242763');
INSERT INTO factions VALUES ('Mother Night', 7, 40, 2, 0, 'Evil', '2009-05-29 17:04:11.254354');
INSERT INTO factions VALUES ('Narodnaya Rasprava', 7, 0, 2, 683, 'Evil', '2009-05-29 17:04:13.268174');
INSERT INTO factions VALUES ('Nightmares', 7, 176, 2, 0, 'Evil', '2009-05-29 17:04:15.281505');
INSERT INTO factions VALUES ('OVERPOWERED', 7, 20, 3, 0, 'Good', '2009-05-29 17:04:17.293415');
INSERT INTO factions VALUES ('Overpowered Republic', 7, 95, 2, 0, 'Good', '2009-05-29 17:04:19.349152');
INSERT INTO factions VALUES ('Pets R Us', 7, 84, 5, 0, 'Good', '2009-05-29 17:04:21.328335');
INSERT INTO factions VALUES ('Pod Six', 7, 28, 2, 0, 'Unaligned', '2009-05-29 17:04:23.345752');
INSERT INTO factions VALUES ('Proentity', 7, 18, 2, 0, 'Good', '2009-05-29 17:04:25.483421');
INSERT INTO factions VALUES ('Railway Hostel', 7, 84, 2, 0, 'Good', '2009-05-29 17:04:27.393304');
INSERT INTO factions VALUES ('Sector House 301', 7, 20, 3, 0, 'Good', '2009-05-29 17:04:29.501481');
INSERT INTO factions VALUES ('Shady Tlacolotl Piracy', 7, 0, 2, 24, 'Evil', '2009-05-29 17:04:31.408723');
INSERT INTO factions VALUES ('StarBuccaneer''s Coffee', 7, 54, 2, 0, 'Evil', '2009-05-29 17:04:33.529265');
INSERT INTO factions VALUES ('Tao of the seven', 7, 6, 3, 0, 'Evil', '2009-05-29 17:04:37.457258');
INSERT INTO factions VALUES ('The "Sex and the City" Girls', 7, 16, 3, 39, 'Unaligned', '2009-05-29 17:04:40.019538');
INSERT INTO factions VALUES ('The Ascended', 7, 5, 3, 0, 'Good', '2009-05-29 17:04:43.068987');
INSERT INTO factions VALUES ('the Catechists', 7, 20, 3, 0, 'Good', '2009-05-29 17:04:44.518288');
INSERT INTO factions VALUES ('The City of Yami-Gaia', 7, 5, 3, 17, 'Evil', '2009-05-29 17:04:46.53928');
INSERT INTO factions VALUES ('The Clock Makers', 7, 96, 3, 0, 'Good', '2009-05-29 17:04:48.575145');
INSERT INTO factions VALUES ('The Crimson Clan', 7, 6, 3, 0, 'Unaligned', '2009-05-29 17:04:50.568777');
INSERT INTO factions VALUES ('The Decepticons', 7, 0, 3, 21, 'Evil', '2009-05-29 17:04:54.093409');
INSERT INTO factions VALUES ('The Kingdom of All Cosmos', 7, 40, 2, 28, 'Good', '2009-05-29 17:04:54.942563');
INSERT INTO factions VALUES ('The Pack', 7, 6, 3, 0, 'Evil', '2009-05-29 17:04:57.626661');
INSERT INTO factions VALUES ('The Street of Gods', 7, 85, 3, 43, 'Good', '2009-05-29 17:04:58.150513');
INSERT INTO factions VALUES ('Viva La Resistance', 7, 6, 2, 1, 'Good', '2009-05-29 17:05:00.175798');
INSERT INTO factions VALUES ('Wardens', 7, 30, 4, 0, 'Good', '2009-05-29 17:05:02.490214');
INSERT INTO factions VALUES ('-Ye olde dead hobo saloon-', 6, 0, 1, 0, 'Evil', '2009-05-29 17:05:04.204185');
INSERT INTO factions VALUES ('77th Armed Response Company', 6, 0, 1, 94, 'Evil', '2009-05-29 17:05:08.242304');
INSERT INTO factions VALUES ('a leper colony', 6, 0, 1, 0, 'Good', '2009-05-29 17:05:10.254335');
INSERT INTO factions VALUES ('Hoodie Ninja Pizza and Subs', 6, 5, 2, 0, 'Good', '2009-05-29 17:09:03.376882');
INSERT INTO factions VALUES ('I Cast Magic Missile!!!', 6, 36, 1, 0, 'Unaligned', '2009-05-29 17:09:05.20269');
INSERT INTO factions VALUES ('I just want a bonus', 6, 0, 0, 0, 'Good', '2009-05-29 17:09:09.233214');
INSERT INTO factions VALUES ('I walk alone....', 6, 0, 1, 24, 'Evil', '2009-05-29 17:09:11.251282');
INSERT INTO factions VALUES ('Infinity', 6, 0, 0, 86, 'Evil', '2009-05-29 17:09:13.264112');
INSERT INTO factions VALUES ('John Brown''s Raiding Party', 6, 30, 1, 0, 'Good', '2009-05-29 17:09:15.272695');
INSERT INTO factions VALUES ('Kingdom of Prismato', 6, 84, 1, 0, 'Good', '2009-05-29 17:09:17.298859');
INSERT INTO factions VALUES ('Legitimate Business Inc.', 6, 10, 1, 0, 'Evil', '2009-05-29 17:09:19.311468');
INSERT INTO factions VALUES ('Martians Anonymous', 6, 0, 1, 31, 'Unaligned', '2009-05-29 17:09:21.323047');
INSERT INTO factions VALUES ('Monday''s Delivery Service', 6, 0, 1, 0, 'Evil', '2009-05-29 17:09:23.335849');
INSERT INTO factions VALUES ('Monsters Under The Bed', 6, 20, 1, 0, 'Evil', '2009-05-29 17:09:25.35937');
INSERT INTO factions VALUES ('Nin''s Jest', 6, 40, 1, 19, 'Good', '2009-05-29 17:09:27.370087');
INSERT INTO factions VALUES ('Ninjas of Circus de Soule', 6, 0, 0, 0, 'Good', '2009-05-29 17:09:29.392084');
INSERT INTO factions VALUES ('nole contendre', 6, 0, 1, 0, 'Good', '2009-05-29 17:09:31.411902');
INSERT INTO factions VALUES ('Oblivion Squadron', 6, 370, 1, 77, 'Evil', '2009-05-29 17:09:33.425568');
INSERT INTO factions VALUES ('Opposites Shining The', 6, 10, 4, 0, 'Good', '2009-05-29 17:09:35.449926');
INSERT INTO factions VALUES ('Order of the Hammer', 6, 45, 1, 0, 'Good', '2009-05-29 17:09:39.479074');
INSERT INTO factions VALUES ('Perfect Peters Playgroup', 6, 5, 2, 0, 'Good', '2009-05-29 17:09:41.497512');
INSERT INTO factions VALUES ('Royal Green Jackets', 6, 6, 1, 0, 'Unaligned', '2009-05-29 17:09:43.513936');
INSERT INTO factions VALUES ('Santa''s Village', 6, 110, 2, 12, 'Good', '2009-05-29 17:09:45.527921');
INSERT INTO factions VALUES ('Singapore', 6, 0, 1, 108, 'Evil', '2009-05-29 17:09:47.539642');
INSERT INTO factions VALUES ('String Cheese Incident', 6, 0, 2, 0, 'Evil', '2009-05-29 17:09:49.55578');
INSERT INTO factions VALUES ('The Abyssal Exalted', 6, 0, 2, 0, 'Evil', '2009-05-29 17:09:51.570022');
INSERT INTO factions VALUES ('The Benevolent Host of Alonai', 6, 0, 1, 0, 'Good', '2009-05-29 17:09:53.589371');
INSERT INTO factions VALUES ('The Circle', 6, 110, 3, 0, 'Good', '2009-05-29 17:09:55.605381');
INSERT INTO factions VALUES ('The Conclave', 6, 11, 0, 0, 'Unaligned', '2009-05-29 17:09:57.624617');
INSERT INTO factions VALUES ('The Forlorn Hope', 6, 0, 1, 2, 'Evil', '2009-05-29 17:09:59.138667');
INSERT INTO factions VALUES ('The Guild of ARCHNESS.', 6, 35, 2, 0, 'Evil', '2009-05-29 17:10:01.160645');
INSERT INTO factions VALUES ('The Guild of Calamitous Intent', 6, 0, 1, 0, 'Evil', '2009-05-29 17:10:03.177219');
INSERT INTO factions VALUES ('The Half-Cent Legion', 6, 5, 3, 0, 'Good', '2009-05-29 17:10:05.194774');
INSERT INTO factions VALUES ('The Heartrender Cabal', 6, 0, 1, 0, 'Evil', '2009-05-29 17:10:07.207162');
INSERT INTO factions VALUES ('The Holistic Detective Agency', 6, 54, 1, 60, 'Unaligned', '2009-05-29 17:10:11.238464');
INSERT INTO factions VALUES ('The Iverson Book Club', 6, 0, 2, 0, 'Unaligned', '2009-05-29 17:10:14.265118');
INSERT INTO factions VALUES ('The Ivory Mark', 6, 25, 3, 0, 'Good', '2009-05-29 17:18:57.932164');
INSERT INTO factions VALUES ('The Killbillies', 6, 10, 2, 0, 'Evil', '2009-05-29 17:18:57.937818');
INSERT INTO factions VALUES ('The Passive Fists of Alonai', 6, 5, 1, 0, 'Good', '2009-05-29 17:18:57.94488');
INSERT INTO factions VALUES ('The Shiney Knights', 6, 96, 1, 0, 'Good', '2009-05-29 17:18:57.953204');
INSERT INTO factions VALUES ('The Spanish Inquisition', 6, 25, 1, 9, 'Good', '2009-05-29 17:18:57.960742');
INSERT INTO factions VALUES ('The Vengabeans', 6, 10, 1, 0, 'Unaligned', '2009-05-29 17:18:58.93987');
INSERT INTO factions VALUES ('The Wild Hunt', 6, 48, 1, 0, 'Unaligned', '2009-05-29 17:19:00.069523');
INSERT INTO factions VALUES ('Umbrella Corporation', 6, 12, 1, 0, 'Unaligned', '2009-05-29 17:19:02.793219');
INSERT INTO factions VALUES ('Void', 6, 0, 1, 69, 'Good', '2009-05-29 17:19:03.732883');
INSERT INTO factions VALUES ('Westhaven', 6, 15, 1, 0, 'Good', '2009-05-29 17:19:06.071067');
INSERT INTO factions VALUES ('You Got Schooled!', 6, 0, 1, 22, 'Evil', '2009-05-29 17:19:08.582331');
INSERT INTO factions VALUES ('Academy B', 5, 20, 1, 0, 'Evil', '2009-05-29 17:19:10.629604');
INSERT INTO factions VALUES ('AROOOO WOLVES AROOOOO', 5, 0, 0, 0, 'Good', '2009-05-29 17:19:12.597925');
INSERT INTO factions VALUES ('DO NOT HIT', 5, 0, 1, 0, 'Evil', '2009-05-29 17:19:15.548921');
INSERT INTO factions VALUES ('Elite Fist of Deathhammer Wing', 5, 0, 1, 0, 'Evil', '2009-05-29 17:19:17.577263');
INSERT INTO factions VALUES ('Everyday Angels', 5, 0, 1, 0, 'Good', '2009-05-29 17:19:19.596632');
INSERT INTO factions VALUES ('Ghostbusters', 5, 0, 1, 0, 'Evil', '2009-05-29 17:19:21.615729');
INSERT INTO factions VALUES ('Knights of the Round Table', 5, 0, 1, 0, 'Good', '2009-05-29 17:19:23.631787');
INSERT INTO factions VALUES ('Mortal Danger', 5, 5, 3, 101, 'Unaligned', '2009-05-29 17:19:25.147732');
INSERT INTO factions VALUES ('Real Life', 5, 0, 1, 0, 'Evil', '2009-05-29 17:19:27.165306');
INSERT INTO factions VALUES ('The Butterfly Brigade', 5, 0, 1, 0, 'Evil', '2009-05-29 17:19:29.181001');
INSERT INTO factions VALUES ('The Call', 5, 5, 1, 0, 'Good', '2009-05-29 17:19:31.205879');
INSERT INTO factions VALUES ('The Corporation', 5, 15, 1, 0, 'Evil', '2009-05-29 17:19:33.225147');
INSERT INTO factions VALUES ('The Fisher King Illuminati', 5, 30, 2, 0, 'Good', '2009-05-29 17:19:35.241007');
INSERT INTO factions VALUES ('The Judgemaster Emporium', 5, 0, 0, 0, 'Good', '2009-05-29 17:19:37.26393');
INSERT INTO factions VALUES ('The San Cristobol Judges', 5, 5, 1, 0, 'Good', '2009-05-29 17:19:39.283937');
INSERT INTO factions VALUES ('The Wanderers', 5, 78, 1, 30, 'Good', '2009-05-29 17:19:41.297063');
INSERT INTO factions VALUES ('Banana peasant militia', 4, 5, 1, 0, 'Good', '2009-05-29 17:19:45.330615');
INSERT INTO factions VALUES ('Border Inc.', 4, 6, 0, 6, 'Evil', '2009-05-29 17:19:47.344046');
INSERT INTO factions VALUES ('Captain Clucky''s', 4, 5, 1, 0, 'Evil', '2009-05-29 17:19:49.359979');
INSERT INTO factions VALUES ('ClanBOB', 4, 5, 2, 0, 'Good', '2009-05-29 17:19:51.374817');
INSERT INTO factions VALUES ('Diablo''s Legion', 4, 0, 0, 0, 'Evil', '2009-05-29 17:19:53.438635');
INSERT INTO factions VALUES ('Magical Love Rainbow Faction', 4, 0, 1, 0, 'Evil', '2009-05-29 17:19:55.583255');
INSERT INTO factions VALUES ('N.C.I.S.', 4, 0, 2, 0, 'Good', '2009-05-29 17:19:57.426951');
INSERT INTO factions VALUES ('The Asylum', 4, 6, 1, 10, 'Good', '2009-05-29 17:19:59.438433');
INSERT INTO factions VALUES ('The Discordian Wanderers', 4, 0, 1, 0, 'Evil', '2009-05-29 17:20:01.488128');
INSERT INTO factions VALUES ('The Wingmen', 4, 25, 3, 22, 'Good', '2009-05-29 17:20:03.543983');
INSERT INTO factions VALUES ('Titty Twister', 4, 0, 1, 0, 'Evil', '2009-05-29 17:20:05.527533');
INSERT INTO factions VALUES ('ZumaPower', 4, 0, 0, 0, 'Evil', '2009-05-29 17:20:07.506636');
INSERT INTO factions VALUES ('Darien Hammerfury', 3, 0, 1, 0, 'Unaligned', '2009-05-29 17:20:09.519735');
INSERT INTO factions VALUES ('Discombobulated Paraphernalia', 3, 0, 1, 0, 'Good', '2009-05-29 17:20:11.536123');
INSERT INTO factions VALUES ('Iverson Homeless Shelter', 3, 5, 1, 0, 'Good', '2009-05-29 17:20:15.571664');
INSERT INTO factions VALUES ('The ReAnimated', 3, 0, 0, 36, 'Unaligned', '2009-05-29 17:20:17.591007');
INSERT INTO factions VALUES ('The Revenants', 3, 0, 0, 0, 'Evil', '2009-05-29 17:20:19.605256');
INSERT INTO factions VALUES ('Westgate', 3, 10, 1, 0, 'Good', '2009-05-29 17:20:21.625097');
INSERT INTO factions VALUES ('March of Carls', 2, 0, 2, 1562, 'Unaligned', '2009-05-29 17:20:23.138309');
INSERT INTO factions VALUES ('XRPG 2', 2, 0, 1, 0, 'Unaligned', '2009-05-29 17:20:25.177764');
INSERT INTO factions VALUES ('9thInfMedical Corps', 1, 0, 1, 0, 'Unaligned', '2009-05-29 17:20:27.166194');
INSERT INTO factions VALUES ('D.W.B. Nifleheim Mission', 1, 11, 1, 0, 'Good', '2009-05-29 17:20:29.185408');
INSERT INTO factions VALUES ('Devil''s Rejects', 1, 108, 0, 0, 'Evil', '2009-05-29 17:20:31.20566');
INSERT INTO factions VALUES ('Dew Evil', 1, 0, 0, 129, 'Evil', '2009-05-29 17:20:33.215805');
INSERT INTO factions VALUES ('Divine Slayers', 1, 0, 1, 0, 'Good', '2009-05-29 17:20:35.222351');
INSERT INTO factions VALUES ('Eternals', 1, 72, 1, 0, 'Unaligned', '2009-05-29 17:20:37.238993');
INSERT INTO factions VALUES ('FATE', 1, 5, 0, 0, 'Good', '2009-05-29 17:20:39.252144');
INSERT INTO factions VALUES ('Foundation of Catholic Priests', 1, 5, 0, 0, 'Good', '2009-05-29 17:20:41.271709');
INSERT INTO factions VALUES ('Gods of Earth', 1, 0, 1, 0, 'Evil', '2009-05-29 17:20:45.295796');
INSERT INTO factions VALUES ('Grand Old Barhah', 1, 150, 1, 0, 'Unaligned', '2009-05-29 17:20:47.305629');
INSERT INTO factions VALUES ('HarperIsland', 1, 15, 1, 84, 'Unaligned', '2009-05-29 17:20:49.322453');
INSERT INTO factions VALUES ('JUICE CREW ALLSTARS', 1, 0, 0, 0, 'Unaligned', '2009-05-29 17:20:51.33668');
INSERT INTO factions VALUES ('Keepers of the Necrowombicon', 1, 5, 1, 0, 'Good', '2009-05-29 17:20:55.369764');
INSERT INTO factions VALUES ('LLAMA LLAMA LLAMA LLAMA LLAMA', 1, 0, 1, 0, 'Unaligned', '2009-05-29 17:20:57.386086');
INSERT INTO factions VALUES ('Lords of the Shinigami', 1, 0, 1, 0, 'Evil', '2009-05-29 17:21:00.415676');
INSERT INTO factions VALUES ('Rob Wray''s Place', 1, 0, 1, 0, 'Unaligned', '2009-05-29 17:21:02.802051');
INSERT INTO factions VALUES ('searchboat', 1, 6, 0, 0, 'Good', '2009-05-29 17:21:04.451051');
INSERT INTO factions VALUES ('SM Imports', 1, 0, 1, 22, 'Unaligned', '2009-05-29 17:21:06.462945');
INSERT INTO factions VALUES ('Sons of Isaac', 1, 30, 0, 0, 'Good', '2009-05-29 17:21:08.48228');
INSERT INTO factions VALUES ('The BLU Team', 1, 30, 0, 0, 'Good', '2009-05-29 17:21:10.493527');
INSERT INTO factions VALUES ('The Hive Mind', 1, 0, 0, 111, 'Unaligned', '2009-05-29 17:21:12.508344');
INSERT INTO factions VALUES ('The Maths Murderers', 1, 5, 0, 0, 'Unaligned', '2009-05-29 17:21:16.544874');
INSERT INTO factions VALUES ('The Sisters of Silence', 1, 0, 1, 0, 'Evil', '2009-05-29 17:21:18.561837');
INSERT INTO factions VALUES ('The Zerg Swarm', 1, 0, 1, 0, 'Evil', '2009-05-29 17:21:20.573247');
INSERT INTO factions VALUES ('Wang Xi', 1, 0, 0, 0, 'Unaligned', '2009-05-29 17:21:22.596005');
INSERT INTO factions VALUES ('Kano Alliance', 1, 0, 0, 0, 'Good', '2009-05-29 17:32:15.46813');


--
-- Data for Name: sh; Type: TABLE DATA; Schema: public; Owner: jchoyt
--

INSERT INTO sh VALUES ('A Clockwork ', 'Nifleheim', 79, 49, '2009-05-07 11:47:13.758692');
INSERT INTO sh VALUES ('Academy B', 'Stygia', 24, 13, '2009-05-07 11:47:13.763931');
INSERT INTO sh VALUES ('Alcoholics Anonymous', 'Purgatorio', 75, 93, '2009-05-07 11:47:13.765184');
INSERT INTO sh VALUES ('All Your Souls', 'Paradise', 15, 26, '2009-05-07 11:47:13.768001');
INSERT INTO sh VALUES ('Alliance of St. Germaine', 'Valhalla', 26, 38, '2009-05-07 11:47:13.771923');
INSERT INTO sh VALUES ('Assassins of the Myst', 'Nifleheim', 59, 14, '2009-05-07 11:47:13.783955');
INSERT INTO sh VALUES ('Atalanta''s Armory', 'Purgatorio', 37, 32, '2009-05-07 11:47:13.788094');
INSERT INTO sh VALUES ('ATHELTHRAL BRIGADE', 'Paradise', 14, 16, '2009-05-07 11:47:13.791919');
INSERT INTO sh VALUES ('Balance', 'Purgatorio', 37, 27, '2009-05-07 11:47:13.803918');
INSERT INTO sh VALUES ('Bazaar of the Bizarre', 'Paradise', 24, 28, '2009-05-07 11:47:13.804682');
INSERT INTO sh VALUES ('Black Goat With 1000 Young', 'Stygian Warrens', 47, 11, '2009-05-07 11:47:13.807252');
INSERT INTO sh VALUES ('Border Inc.', 'Stygia', 45, 9, '2009-05-07 11:47:13.811961');
INSERT INTO sh VALUES ('CANADA', 'Paradise', 18, 25, '2009-05-07 11:47:13.819937');
INSERT INTO sh VALUES ('Chaotic Evil!!!', 'Nifleheim', 13, 16, '2009-05-07 11:47:13.825347');
INSERT INTO sh VALUES ('Children of Sekhmet', 'Valhalla', 24, 42, '2009-05-07 11:47:13.827892');
INSERT INTO sh VALUES ('Church of the Resurrection', 'Paradise', 18, 14, '2009-05-07 11:47:13.832055');
INSERT INTO sh VALUES ('Clan Hellstrom', 'Stygia', 21, 15, '2009-05-07 11:47:13.836633');
INSERT INTO sh VALUES ('ClanBOB', 'Paradise', 17, 29, '2009-05-07 11:47:13.840136');
INSERT INTO sh VALUES ('Coven of the Blood Moon', 'Nifleheim', 77, 53, '2009-05-07 11:47:13.848263');
INSERT INTO sh VALUES ('Covert Moles', 'Paradise', 20, 31, '2009-05-07 11:47:13.852751');
INSERT INTO sh VALUES ('Critters', 'Valhalla', 22, 33, '2009-05-07 11:47:13.863102');
INSERT INTO sh VALUES ('Crystalmir Corporation', 'Nifleheim', 74, 54, '2009-05-07 11:47:13.873899');
INSERT INTO sh VALUES ('Damage, Inc.', 'Purgatorio', 43, 18, '2009-05-07 11:47:13.875126');
INSERT INTO sh VALUES ('Damned Librarians', 'Stygia', 38, 17, '2009-05-07 11:47:13.880616');
INSERT INTO sh VALUES ('Dark Horde', 'Stygian Warrens', 27, 9, '2009-05-07 11:47:13.884334');
INSERT INTO sh VALUES ('Dead End', 'Valhalla', 27, 33, '2009-05-07 11:47:13.892081');
INSERT INTO sh VALUES ('Devil''s Rejects', 'Purgatorio', 74, 93, '2009-05-07 11:47:13.899971');
INSERT INTO sh VALUES ('Dol Guldur', 'Stygia', 21, 14, '2009-05-07 11:47:13.908143');
INSERT INTO sh VALUES ('Doodles', 'Stygia', 27, 3, '2009-05-07 11:47:13.912339');
INSERT INTO sh VALUES ('Dunnel Hills Police Department', 'Paradise', 19, 29, '2009-05-07 11:47:13.925216');
INSERT INTO sh VALUES ('Endangered Species', 'Purgatorio', 67, 20, '2009-05-07 11:47:13.9281');
INSERT INTO sh VALUES ('Eternals', 'Purgatorio', 64, 22, '2009-05-07 11:47:13.935593');
INSERT INTO sh VALUES ('Faber Castell Free Rolling Ink', 'Stygian Warrens', 6, 3, '2009-05-07 11:47:13.940351');
INSERT INTO sh VALUES ('Falkenberg''s Legion''', 'Nifleheim', 58, 14, '2009-05-07 11:47:13.944242');
INSERT INTO sh VALUES ('Fermentation Inc.', 'Nifleheim', 31, 66, '2009-05-07 11:47:13.94802');
INSERT INTO sh VALUES ('Fight Club', 'Purgatorio', 82, 85, '2009-05-07 11:47:13.958126');
INSERT INTO sh VALUES ('Getsmart', 'Paradise', 19, 26, '2009-05-07 11:47:13.971164');
INSERT INTO sh VALUES ('Glimmering Crusaders', 'Paradise', 19, 13, '2009-05-07 11:47:13.979801');
INSERT INTO sh VALUES ('GMT Breakfast Club', 'Nifleheim', 32, 67, '2009-05-07 11:47:13.987907');
INSERT INTO sh VALUES ('Grizzled Geezer', 'Valhalla', 19, 35, '2009-05-07 11:47:13.994941');
INSERT INTO sh VALUES ('Hell''s Angels', 'Paradise', 22, 32, '2009-05-07 11:47:14.007208');
INSERT INTO sh VALUES ('Imperium Romanum', 'Valhalla', 21, 44, '2009-05-07 11:47:14.011256');
INSERT INTO sh VALUES ('Keepers of the Necrowombicon', 'Valhalla', 16, 44, '2009-05-07 11:47:14.020746');
INSERT INTO sh VALUES ('Lack Confederacy', 'Paradise', 21, 31, '2009-05-07 11:47:14.030845');
INSERT INTO sh VALUES ('LOLCATS', 'Stygia', 13, 14, '2009-05-07 11:47:14.035103');
INSERT INTO sh VALUES ('Monsters Under The Bed', 'Stygia', 50, 6, '2009-05-07 11:47:14.039148');
INSERT INTO sh VALUES ('Mortal Danger', 'Valhalla', 22, 43, '2009-05-07 11:47:14.043207');
INSERT INTO sh VALUES ('Mother Night', 'Stygia', 33, 4, '2009-05-07 11:47:14.052354');
INSERT INTO sh VALUES ('Murder Inc.', 'Stygian Warrens', 34, 16, '2009-05-07 11:47:14.055031');
INSERT INTO sh VALUES ('Necroscope', 'Valhalla', 22, 47, '2009-05-07 11:47:14.063025');
INSERT INTO sh VALUES ('Newbie Welcome Wagon', 'Valhalla', 29, 45, '2009-05-07 11:47:14.067142');
INSERT INTO sh VALUES ('Nightmares', 'Stygia', 28, 15, '2009-05-07 11:47:14.075803');
INSERT INTO sh VALUES ('Novus Ordo', 'Paradise', 15, 10, '2009-05-07 11:47:14.083144');
INSERT INTO sh VALUES ('Oblivion Squadron', 'Stygia', 41, 13, '2009-05-07 11:47:14.092022');
INSERT INTO sh VALUES ('Opposites Shining The', 'Valhalla', 25, 39, '2009-05-07 11:47:14.096039');
INSERT INTO sh VALUES ('Ordo Malleus sorry', 'Purgatorio', 45, 47, '2009-05-07 11:47:14.100289');
INSERT INTO sh VALUES ('OTHERKINS', 'Paradise', 21, 30, '2009-05-07 11:47:14.104229');
INSERT INTO sh VALUES ('Pirates of R''lyeh', 'Stygia', 1, 18, '2009-05-07 11:47:14.108111');
INSERT INTO sh VALUES ('Railway Hostel', 'Nifleheim', 26, 20, '2009-05-07 11:47:14.112268');
INSERT INTO sh VALUES ('Random Goodness', 'Paradise', 25, 32, '2009-05-07 11:47:14.116016');
INSERT INTO sh VALUES ('Random Shocking Violence', 'Purgatorio', 73, 96, '2009-05-07 11:47:14.120035');
INSERT INTO sh VALUES ('Sanitarium', 'Stygia', 36, 15, '2009-05-07 11:47:14.132095');
INSERT INTO sh VALUES ('Satan''s Workshop', 'Valhalla', 30, 43, '2009-05-07 11:47:14.136239');
INSERT INTO sh VALUES ('Seekers of the Void', 'Valhalla', 26, 40, '2009-05-07 11:47:14.140351');
INSERT INTO sh VALUES ('Sentinels', 'Nifleheim', 61, 17, '2009-05-07 11:47:14.144205');
INSERT INTO sh VALUES ('Serenity', 'Paradise', 19, 33, '2009-05-07 11:47:14.148008');
INSERT INTO sh VALUES ('Serious Business', 'Stygian Warrens', 43, 5, '2009-05-07 11:47:14.15204');
INSERT INTO sh VALUES ('Servants of the Void', 'Stygia', 31, 10, '2009-05-07 11:47:14.155996');
INSERT INTO sh VALUES ('Shady Tlacolotl Piracy', 'Stygian Warrens', 26, 9, '2009-05-07 11:47:14.164548');
INSERT INTO sh VALUES ('Soldiers of Duat', 'Purgatorio', 83, 88, '2009-05-07 11:47:14.168586');
INSERT INTO sh VALUES ('StarBuccaneer''s Coffee', 'Nifleheim', 22, 21, '2009-05-07 11:47:14.172366');
INSERT INTO sh VALUES ('Status Quo', 'Purgatorio', 41, 20, '2009-05-07 11:47:14.176225');
INSERT INTO sh VALUES ('Suburban Ed''s Peasant Militia', 'Nifleheim', 24, 65, '2009-05-07 11:47:14.180107');
INSERT INTO sh VALUES ('Tao of the seven', 'Purgatorio', 31, 38, '2009-05-07 11:47:14.183997');
INSERT INTO sh VALUES ('Templar Guy Crew', 'Paradise', 17, 30, '2009-05-07 11:47:14.187658');
INSERT INTO sh VALUES ('The Abyssal Exalted', 'Stygia', 44, 1, '2009-05-07 11:47:14.192021');
INSERT INTO sh VALUES ('The Alchemist''s Guild', 'Paradise', 22, 29, '2009-05-07 11:47:14.19605');
INSERT INTO sh VALUES ('The Ascended', 'Paradise', 16, 29, '2009-05-07 11:47:14.200035');
INSERT INTO sh VALUES ('The Bermuda Club International', 'Paradise', 16, 11, '2009-05-07 11:47:14.204433');
INSERT INTO sh VALUES ('The BLU Team', 'Nifleheim', 18, 72, '2009-05-07 11:47:14.208168');
INSERT INTO sh VALUES ('The Brotherhood', 'Paradise', 18, 12, '2009-05-07 11:47:14.212039');
INSERT INTO sh VALUES ('The Circle', 'Paradise', 10, 18, '2009-05-07 11:47:14.215987');
INSERT INTO sh VALUES ('The City Watch', 'Nifleheim', 62, 18, '2009-05-07 11:47:14.220008');
INSERT INTO sh VALUES ('The Claw', 'Stygia', 30, 17, '2009-05-07 11:47:14.224034');
INSERT INTO sh VALUES ('The Clock Makers', 'Valhalla', 22, 35, '2009-05-07 11:47:14.228233');
INSERT INTO sh VALUES ('The Corporation', 'Stygia', 6, 18, '2009-05-07 11:47:14.232338');
INSERT INTO sh VALUES ('The Cult of the Spork', 'Valhalla', 32, 45, '2009-05-07 11:47:14.248639');
INSERT INTO sh VALUES ('The Death Star', 'Stygian Warrens', 18, 6, '2009-05-07 11:47:14.256082');
INSERT INTO sh VALUES ('The Demonic Horde', 'Stygia', 49, 6, '2009-05-07 11:47:14.259761');
INSERT INTO sh VALUES ('The Demons Next Door', 'Stygia', 3, 7, '2009-05-07 11:47:14.264189');
INSERT INTO sh VALUES ('The Divine Slayers', 'Paradise', 20, 15, '2009-05-07 11:47:14.273752');
INSERT INTO sh VALUES ('The Evil League of Evil', 'Stygia', 20, 0, '2009-05-07 11:47:14.278237');
INSERT INTO sh VALUES ('The Faithful', 'Paradise', 22, 25, '2009-05-07 11:47:14.28404');
INSERT INTO sh VALUES ('The Fat Duck', 'Stygia', 1, 14, '2009-05-07 11:47:14.288075');
INSERT INTO sh VALUES ('The Feathered Foe', 'Nifleheim', 30, 63, '2009-05-07 11:47:14.292074');
INSERT INTO sh VALUES ('The Fridge', 'Valhalla', 18, 33, '2009-05-07 11:47:14.296369');
INSERT INTO sh VALUES ('The Grey Fedora Gang', 'Nifleheim', 29, 67, '2009-05-07 11:47:14.305207');
INSERT INTO sh VALUES ('The Guild of ARCHNESS.', 'Stygia', 41, 9, '2009-05-07 11:47:14.308006');
INSERT INTO sh VALUES ('The Guild of Calamitous Intent', 'Stygia', 47, 18, '2009-05-07 11:47:14.312011');
INSERT INTO sh VALUES ('The Ivory Mark', 'Paradise', 16, 26, '2009-05-07 11:47:14.324275');
INSERT INTO sh VALUES ('The Kakistrocratic Genocide', 'Stygia', 41, 15, '2009-05-07 11:47:14.334376');
INSERT INTO sh VALUES ('The Killbillies', 'Stygia', 42, 2, '2009-05-07 11:47:14.340536');
INSERT INTO sh VALUES ('The Knights Templar', 'Paradise', 16, 28, '2009-05-07 11:47:14.343998');
INSERT INTO sh VALUES ('The League of Science-Heroes', 'Valhalla', 16, 41, '2009-05-07 11:47:14.348038');
INSERT INTO sh VALUES ('The Passive Fists of Alonai', 'Paradise', 18, 15, '2009-05-07 11:47:14.35261');
INSERT INTO sh VALUES ('The Phoenix', 'Purgatorio', 35, 23, '2009-05-07 11:47:14.355353');
INSERT INTO sh VALUES ('The Rambling Drunks', 'Stygian Warrens', 4, 2, '2009-05-07 11:47:14.36451');
INSERT INTO sh VALUES ('The Robot Mafia', 'Stygia', 14, 15, '2009-05-07 11:47:14.37622');
INSERT INTO sh VALUES ('The Scourge', 'Stygia', 44, 3, '2009-05-07 11:47:14.380488');
INSERT INTO sh VALUES ('The Searchers', 'Paradise', 14, 28, '2009-05-07 11:47:14.383252');
INSERT INTO sh VALUES ('The Stain Gang', 'Stygia', 18, 4, '2009-05-07 11:47:14.388115');
INSERT INTO sh VALUES ('The Union of Red Onion', 'Nifleheim', 62, 16, '2009-05-07 11:47:14.394594');
INSERT INTO sh VALUES ('The Vengeful Wrath of Hashaa', 'Nifleheim', 30, 61, '2009-05-07 11:47:14.404453');
INSERT INTO sh VALUES ('Torchwood', 'Valhalla', 21, 38, '2009-05-07 11:47:14.412219');
INSERT INTO sh VALUES ('Trinitas', 'Paradise', 16, 7, '2009-05-07 11:47:14.41656');
INSERT INTO sh VALUES ('Undeadites', 'Stygia', 41, 10, '2009-05-07 11:47:14.419273');
INSERT INTO sh VALUES ('Vicious Carnage', 'Stygia', 16, 6, '2009-05-07 11:47:14.424008');
INSERT INTO sh VALUES ('Westgate', 'Valhalla', 23, 42, '2009-05-07 11:47:14.428008');
INSERT INTO sh VALUES ('Where The Wild Things Are', 'Paradise', 18, 32, '2009-05-07 11:47:14.431974');
INSERT INTO sh VALUES ('Wolf''s Dragoons', 'Paradise', 15, 31, '2009-05-07 11:47:14.43601');
INSERT INTO sh VALUES ('Ye Olde Component Shoppe', 'Valhalla', 27, 43, '2009-05-07 11:47:14.440034');
INSERT INTO sh VALUES ('Zpies', 'Nifleheim', 28, 73, '2009-05-07 11:47:14.444181');
INSERT INTO sh VALUES ('Clothed with Authority', 'Purgatorio', 63, 77, '2009-05-28 10:10:20.738196');
INSERT INTO sh VALUES ('The Shiney Knights', 'Purgatorio', 64, 77, '2009-05-28 10:10:20.866557');
INSERT INTO sh VALUES ('CHAOS CHAOS CHAOS', 'Purgatorio', 62, 81, '2009-05-28 10:10:20.875222');
INSERT INTO sh VALUES ('Clan McCrea', 'Purgatorio', 58, 85, '2009-05-28 10:10:20.895612');
INSERT INTO sh VALUES ('Thari Squadron', 'Purgatorio', 64, 85, '2009-05-28 10:10:21.629046');
INSERT INTO sh VALUES ('Rogue''s Guild', 'Purgatorio', 78, 79, '2009-05-28 10:10:25.162041');
INSERT INTO sh VALUES ('Shadow', 'Purgatorio', 80, 80, '2009-05-28 10:10:27.178298');
INSERT INTO sh VALUES ('Kommando Dosenbier', 'Purgatorio', 75, 81, '2009-05-28 10:10:29.20237');
INSERT INTO sh VALUES ('Fervant Apathy', 'Purgatorio', 67, 85, '2009-05-28 10:10:31.222366');
INSERT INTO sh VALUES ('Deadland Outlaws', 'Purgatorio', 79, 86, '2009-05-28 10:10:33.235632');
INSERT INTO sh VALUES ('Angels with Dirty Faces', 'Purgatorio', 83, 86, '2009-05-28 10:10:35.250432');
INSERT INTO sh VALUES ('The Watcherse.', 'Purgatorio', 66, 88, '2009-05-28 10:10:37.290076');
INSERT INTO sh VALUES ('Team Laser Explosion', 'Valhalla', 9, 2, '2009-05-28 10:10:39.603607');
INSERT INTO sh VALUES ('OVERPOWERED', 'Valhalla', 15, 3, '2009-05-28 10:10:41.622881');
INSERT INTO sh VALUES ('The Friends of English Magic', 'Valhalla', 4, 4, '2009-05-28 10:10:45.15256');
INSERT INTO sh VALUES ('The San Cristobol Judges', 'Valhalla', 5, 4, '2009-05-28 10:10:47.167194');
INSERT INTO sh VALUES ('Just Leave Me Alone', 'Valhalla', 14, 7, '2009-05-28 10:10:49.188731');
INSERT INTO sh VALUES ('Guardian', 'Valhalla', 15, 7, '2009-05-28 10:10:51.197398');
INSERT INTO sh VALUES ('Transmigrated Afterlife Party', 'Valhalla', 12, 8, '2009-05-28 10:10:53.216591');
INSERT INTO sh VALUES ('The Sewer Rats', 'Valhalla', 7, 10, '2009-05-28 10:10:55.231865');
INSERT INTO sh VALUES ('Magi Anonymous', 'Valhalla', 6, 13, '2009-05-28 10:10:57.251895');
INSERT INTO sh VALUES ('The Legion', 'Valhalla', 12, 13, '2009-05-28 10:10:59.269852');
INSERT INTO sh VALUES ('Iverson Homeless Shelter', 'Valhalla', 5, 14, '2009-05-28 10:11:01.277226');
INSERT INTO sh VALUES ('Children of the Lightbulb', 'Valhalla', 15, 18, '2009-05-28 10:11:03.294552');
INSERT INTO sh VALUES ('Wright''s Corridor Free Clinic', 'Valhalla', 16, 19, '2009-05-28 10:11:05.314671');
INSERT INTO sh VALUES ('Grand Old Barhah', 'Valhalla', 5, 20, '2009-05-28 10:11:07.327618');
INSERT INTO sh VALUES ('The Necrotic Carnival', 'Nifleheim', 20, 14, '2009-05-28 10:11:09.347344');
INSERT INTO sh VALUES ('The Wanderers', 'Nifleheim', 20, 17, '2009-05-28 10:11:11.358659');
INSERT INTO sh VALUES ('Viva La Resistance', 'Nifleheim', 17, 18, '2009-05-28 10:11:15.398335');
INSERT INTO sh VALUES ('God Inc.', 'Nifleheim', 19, 20, '2009-05-28 10:11:17.403468');
INSERT INTO sh VALUES ('Busty''s Littlest Whorehouse', 'Nifleheim', 17, 22, '2009-05-28 10:11:19.671818');
INSERT INTO sh VALUES ('Divine Emergency Management', 'Nifleheim', 19, 23, '2009-05-28 10:11:21.659653');
INSERT INTO sh VALUES ('Death''s Soul Collectors', 'Nifleheim', 22, 24, '2009-05-28 10:11:23.447655');
INSERT INTO sh VALUES ('Nothing Else Matters', 'Nifleheim', 59, 10, '2009-05-28 10:11:25.463864');
INSERT INTO sh VALUES ('Falkenberg''s Legion', 'Nifleheim', 58, 14, '2009-05-28 10:11:27.473774');
INSERT INTO sh VALUES ('Heart of Oak', 'Nifleheim', 56, 15, '2009-05-28 10:11:29.503998');
INSERT INTO sh VALUES ('The Holistic Detective Agency', 'Nifleheim', 59, 20, '2009-05-28 10:11:31.507069');
INSERT INTO sh VALUES ('Doom Warders', 'Nifleheim', 56, 31, '2009-05-28 10:11:33.518718');
INSERT INTO sh VALUES ('NADA', 'Nifleheim', 53, 35, '2009-05-28 10:11:35.530288');
INSERT INTO sh VALUES ('Ridleytown Resistance Front', 'Nifleheim', 52, 39, '2009-05-28 10:11:37.539476');
INSERT INTO sh VALUES ('Friends of BK', 'Nifleheim', 48, 41, '2009-05-28 10:11:39.554568');
INSERT INTO sh VALUES ('The Crimson Clan', 'Nifleheim', 52, 42, '2009-05-28 10:11:41.5625');
INSERT INTO sh VALUES ('Long Distance Ghosts', 'Nifleheim', 56, 44, '2009-05-28 10:11:45.593692');
INSERT INTO sh VALUES ('The Wild Hunt', 'Nifleheim', 82, 44, '2009-05-28 10:11:47.622487');
INSERT INTO sh VALUES ('Axes High', 'Nifleheim', 76, 45, '2009-05-28 10:11:49.63273');
INSERT INTO sh VALUES ('The Council', 'Nifleheim', 74, 47, '2009-05-28 10:11:51.150384');
INSERT INTO sh VALUES ('NRA Nutjobs', 'Nifleheim', 74, 48, '2009-05-28 10:11:53.162348');
INSERT INTO sh VALUES ('Forsaken', 'Nifleheim', 81, 48, '2009-05-28 10:11:55.17881');
INSERT INTO sh VALUES ('Crypt Lords of Hashaa', 'Nifleheim', 78, 55, '2009-05-28 10:11:57.190274');
INSERT INTO sh VALUES ('Pets R Us', 'Nifleheim', 76, 56, '2009-05-28 10:11:59.202406');
INSERT INTO sh VALUES ('The Hellbound', 'Stygian Warrens', 8, 6, '2009-05-28 10:12:01.226599');
INSERT INTO sh VALUES ('I Cast Magic Missile!!!', 'Stygian Warrens', 18, 6, '2009-05-28 10:12:03.240235');
INSERT INTO sh VALUES ('The Dark Oppressors Guild', 'Stygian Warrens', 19, 13, '2009-05-28 10:12:05.26275');


--
-- Name: factions_pkey; Type: CONSTRAINT; Schema: public; Owner: jchoyt; Tablespace: 
--

ALTER TABLE ONLY factions
    ADD CONSTRAINT factions_pkey PRIMARY KEY (faction);


--
-- Name: sh_pkey; Type: CONSTRAINT; Schema: public; Owner: jchoyt; Tablespace: 
--

ALTER TABLE ONLY sh
    ADD CONSTRAINT sh_pkey PRIMARY KEY (faction);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

