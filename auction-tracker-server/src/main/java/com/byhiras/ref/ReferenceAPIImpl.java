package com.byhiras.ref;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.byhiras.ref.model.Auction;
import com.byhiras.ref.model.AuctionPaddle;
import com.byhiras.ref.model.Catalog;
import com.byhiras.ref.model.Lot;
import com.byhiras.ref.model.RefId;
import com.byhiras.ref.model.RefItem;
import com.byhiras.ref.model.User;
import com.byhiras.ref.repo.AuctionPaddleRepository;
import com.byhiras.ref.repo.AuctionRepository;
import com.byhiras.ref.repo.CatalogRepository;
import com.byhiras.ref.repo.LotRepository;
import com.byhiras.ref.repo.UserRepository;

@Service
public class ReferenceAPIImpl implements ReferenceApi {

	private Map<Class<? extends RefItem>, CrudRepository<? extends RefItem, RefId>> refRepositories = new HashMap<>();

	@Inject
	public ReferenceAPIImpl(final AuctionRepository auctionRepo, final UserRepository userRepo,
			final AuctionPaddleRepository auctionPaddleRepo, final CatalogRepository catalogRepo, final LotRepository lotRepo) {
		refRepositories.put(Auction.class, auctionRepo);
		refRepositories.put(User.class, userRepo);
		refRepositories.put(AuctionPaddle.class, auctionPaddleRepo);
		refRepositories.put(Catalog.class, catalogRepo);
		refRepositories.put(Lot.class, lotRepo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends RefItem> T getById(final Class<T> t, final RefId id) {		
		return (T) refRepositories.get(t).findOne(id);
	}

	@Override
	public <T extends RefItem> CrudRepository<? extends RefItem, RefId> getRepository(final Class<T> t) {
		return refRepositories.get(t);
	}
}
