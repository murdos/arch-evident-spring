/*
 * Copyright 2022-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.order;

import example.customer.Customer;
import example.customer.Customer.CustomerId;
import example.order.Order.LineItem.LineItemId;
import example.order.Order.OrderId;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Association;
import org.jmolecules.ddd.types.Entity;
import org.jmolecules.ddd.types.Identifier;

@Getter
public class Order {

	private final OrderId id;
	private final CustomerId customer;
	private Status status;
	private final List<LineItem> lineItems = new ArrayList<>();

	public Order(CustomerId customerId) {
		this.id = new OrderId(UUID.randomUUID());
		this.status = Status.OPEN;
		this.customer = customerId;
	}

	OrderCompleted complete() {
		this.status = Status.COMPLETED;
		return new OrderCompleted(this.id);
	}

	Order add(LineItem item) {

		this.lineItems.add(item);

		return this;
	}

	public record OrderId(UUID id) implements Identifier {}

	enum Status {
		OPEN, COMPLETED, CANCELLED;
	}

	@Getter
	static class LineItem {

		private final LineItemId id;
		private final String description;
		private final long amount;

		LineItem(String description, long amount) {

			this.id = new LineItemId(UUID.randomUUID());
			this.description = description;
			this.amount = amount;
		}

		record LineItemId(UUID id) implements Identifier {}
	}
}
