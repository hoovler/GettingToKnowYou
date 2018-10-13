package game.persistence;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PlayerHistoryService {
	private static final Logger log = LoggerFactory.getLogger(PlayerHistoryService.class);
}
