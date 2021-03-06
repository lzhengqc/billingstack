package com.billingstack

import grails.converters.JSON

class Plan extends BillingEntity {

	Merchant merchant

	String name
	String title
	String description
	
	String provider
	
	String metadata
	
	Date dateCreated
	Date lastUpdated

  static constraints = {
		merchant(unique : 'name')
		title nullable : true
		description nullable : true
		provider nullable : true
		metadata nullable : true
  }

  static mapping = {
		metadata type: 'text'
  }

  def serialize(plan) {
    [
        'id' : id,
        'name' : name,
        'title' : title,
        'description' : description,
				'provider' : provider,
        'products' : PlanProduct.findAllByPlan(this).collect { it.serialize() },
        'metadata' : metadata ? JSON.parse(metadata) : [:]
    ]
  }

}

