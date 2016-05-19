package dao

import javax.inject.Inject

import com.google.inject.Singleton
import models._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by Canh on 5/14/2016.
  */

@Singleton
class AddressDAOImpl @Inject() (dbConfigProvider: DatabaseConfigProvider) extends AddressDAO {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class AddressTable(tag: Tag) extends Table[Address](tag,"address") {

    def id = column[Int]("address_id", O.PrimaryKey, O.AutoInc)
    def streetName = column[String]("street_name")
    def apartmentNumber = column[String]("apartment_number")
    def city = column[String]("city")
    def state = column[String]("state")
    def country = column[String]("country")
    def zipcode = column[String]("zipcode")

    override def * = (id, schemaName, apartmentNumber, city, state, country, zipcode) <> (Address.tupled, Address.unapply)
  }

  implicit val addressTableQuery = TableQuery[AddressTable]

  override def add(address: Address): Future[String] = ???

  override def get(id: Long): Future[Option[Address]] = ???

  override def delete(id: Long): Future[Int] = ???

  override def listAll: Future[Seq[Address]] = ???
}
