CREATE TABLE IF NOT EXISTS `userServiceDb`.`USERS`
(
    `ID`             INT         NOT NULL AUTO_INCREMENT,
    `USERNAME`       VARCHAR(45) NOT NULL,
    `PASSWORD`       VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC) VISIBLE
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `userServiceDb`.`ROLES`
                    (
                        `ID`         INT         NOT NULL AUTO_INCREMENT,
                        PRIMARY KEY (`ID`)
                    )
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `userServiceDb`.`TOKENS`
(
    `ID`         INT         NOT NULL AUTO_INCREMENT,
    `ACCESS_TOKEN`       VARCHAR(255) NOT NULL,
    `REFRESH_TOKEN`       VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID`)
)
    ENGINE = InnoDB;