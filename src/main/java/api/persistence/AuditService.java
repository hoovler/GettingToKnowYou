package api.persistence;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AuditService {
	private static final Logger log = LoggerFactory.getLogger(AuditService.class);
}
