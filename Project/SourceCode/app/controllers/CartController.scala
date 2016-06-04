package controllers

import com.google.inject.Inject
import models.{Cart, CartForm}
import models.{Cart_Item, CartItemForm}
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc.{Action, Controller}
import service.CartService
import service.CartItemService

import scala.concurrent.Future

/**
 * The Cart controller.
  *
  * @param messagesApi The Play messages API.
 * @param cartService The user service implementation.
 */
 class CartController @Inject()(cartService: CartService,cartItemService: CartItemService, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  /** List Cart*/
  /*def listCart = Action.async { implicit  request =>
  cartService.listAllCarts map { carts =>
      Ok(views.html.shopping_cart(CartForm.form,carts))
    }
  }

  /** Add Cart.*/
  def addCart() = Action.async { implicit request =>
  CartForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.shopping_cart(errorForm,Seq.empty[Cart]))),
      data => {
        val newCart = new Cart(0,null,data.grandTotal)
        cartService.addCart(newCart).map(res =>
          Redirect(routes.CartController.listCart()).flashing(Messages("flash.success") -> res)
        )
      })
  }
  
  /** Delete Cart.*/
  def deleteCart(id : Int) = Action.async { implicit request =>
  cartService.deleteCart(id) map { res =>
      Redirect(routes.CartController.listCart())
    }
  }*/


  /** List Cart*/
  def listCartItem = Action.async { implicit  request =>
    cartItemService.listAllCartItems map { cartItems =>
      Ok(views.html.shopping_cart(CartItemForm.form,cartItems))
    }
  }

  /** Add Cart.*/
  def addCartItem(cusId: Int, proId: Int) = Action.async { implicit request =>
     /* val cardId =  cartService.getCartId(cusId).id*/
        val newItemCart = Cart_Item(0,0,proId)
        cartItemService.addCartItem(newItemCart).map(res =>
          Redirect(routes.CartController.listCartItem()).flashing(Messages("flash.success") -> res)
        )
  }

  /** Delete Cart.*/
  def deleteCartItem(id : Int) = Action.async { implicit request =>
    cartItemService.deleteCartItem(id) map { res =>
      Redirect(routes.CartController.listCartItem())
    }
  }
}
