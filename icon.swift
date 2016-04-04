import UIKit
import RxSwift
import Cartography

class HandleDisplayView: UIView {


    private var disposeBag = DisposeBag()

    private let limit = 5

    var handleImageView: UIImageView

    var oneSpriteImageView = SpriteImageView()
    var twoSpriteImageViews = [SpriteImageView(), SpriteImageView()]
    var threeSpriteImageViews = [SpriteImageView(), SpriteImageView(), SpriteImageView()]
    var fourSpriteImageViews = [SpriteImageView(), SpriteImageView(), SpriteImageView(), SpriteImageView()]
    var fiveSpriteImageViews = [SpriteImageView(), SpriteImageView(), SpriteImageView(), SpriteImageView()]

    let baseFrame = CGRectMake(0,0,48,48)

    init(){
        handleImageView = UIImageView(frame: baseFrame)
        handleImageView.contentMode = .ScaleAspectFill
        handleImageView.layer.masksToBounds = true
        handleImageView.layer.cornerRadius = 5
        handleImageView.layer.borderColor = UIColor.flatGrayColor().CGColor
        handleImageView.layer.borderWidth = 1.0

        super.init(frame: baseFrame)

        addSubview(handleImageView)
        addSubview(oneSpriteImageView)
        twoSpriteImageViews.forEach({ self.addSubview($0) })
        threeSpriteImageViews.forEach({ self.addSubview($0) })
        fourSpriteImageViews.forEach({ self.addSubview($0) })
        fiveSpriteImageViews.forEach({ self.addSubview($0)})

        constrain(handleImageView, oneSpriteImageView) { (handleImageView, oneSpriteImageView) -> () in
            handleImageView.left == handleImageView.superview!.left
            handleImageView.right == handleImageView.superview!.right
            handleImageView.top == handleImageView.superview!.top
            handleImageView.bottom == handleImageView.superview!.bottom

            oneSpriteImageView.left == oneSpriteImageView.superview!.left
            oneSpriteImageView.right == oneSpriteImageView.superview!.right
            oneSpriteImageView.top == oneSpriteImageView.superview!.top
            oneSpriteImageView.bottom == oneSpriteImageView.superview!.bottom
        }

        constrain(twoSpriteImageViews) { (twoSpriteImageViews) -> () in
            let first = twoSpriteImageViews[0]
            let second = twoSpriteImageViews[1]

            let cellLength = first.superview!.width * 0.75

            first.height == cellLength
            first.width == cellLength

            first.left == first.superview!.left
            first.top == first.superview!.top

            second.height == cellLength
            second.width == cellLength

            second.right == second.superview!.right
            second.bottom == second.superview!.bottom
        }

        constrain(threeSpriteImageViews) { (threeSpriteImageViews) -> () in
            let first = threeSpriteImageViews[0]
            let second = threeSpriteImageViews[1]
            let third = threeSpriteImageViews[2]

            let cellLength = first.superview!.width * 0.66

            first.height == cellLength
            first.width == cellLength

            first.left == second.superview!.left
            first.top == second.superview!.top

            second.height == cellLength
            second.width == cellLength

            second.right == third.superview!.right
            second.top == third.superview!.top


            third.height == cellLength
            third.width == cellLength

            third.centerX == first.superview!.centerX
            third.bottom == first.superview!.bottom
        }

    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }


    func populateWithSpriteUrls(spriteUrls: [String]){
        hideEverything()
        var finalSpriteUrls = spriteUrls
        if finalSpriteUrls.count == 1 {
            oneSpriteImageView.hidden = false
            oneSpriteImageView.simpleLoad(finalSpriteUrls[0])
        }
        if finalSpriteUrls.count == 2 {
            for (index, spriteUrl) in finalSpriteUrls.enumerate() {
                twoSpriteImageViews[index].hidden = false
                twoSpriteImageViews[index].simpleLoad(spriteUrl)
            }
        }
        if finalSpriteUrls.count == 3 {
            for (index, spriteUrl) in finalSpriteUrls.enumerate() {
                threeSpriteImageViews[index].hidden = false
                threeSpriteImageViews[index].simpleLoad(spriteUrl)
            }
        }
        if finalSpriteUrls.count == 4 {
            for (index, spriteUrl) in finalSpriteUrls.enumerate() {
                fourSpriteImageViews[index].hidden = false
                fourSpriteImageViews[index].simpleLoad(spriteUrl)
            }
        }
    }

    func populateWithImageUrl(imageUrl: String){
        hideEverything()
        handleImageView.hidden = false
        handleImageView.simpleLoad(imageUrl)
    }

    private func hideEverything(){
        handleImageView.hidden = true
        oneSpriteImageView.hidden = true
        twoSpriteImageViews.forEach({ $0.hidden = true })
        threeSpriteImageViews.forEach({ $0.hidden = true })
        fourSpriteImageViews.forEach({ $0.hidden = true })
        fiveSpriteImageViews.forEach({ $0.hidden = true })
    }

    func configureWithHandleId(handleId: String){
        disposeBag = DisposeBag()

        let rx_handle = HandleService().rx_observeHandle(handleId).shareReplayLatestWhileConnected()
        let rx_users = HandleService().rx_observeHandleUsers(handleId)

        Observable.combineLatest(rx_handle, rx_users) { (handle, users) -> (handle: HandleModel, users: [UserModel]) in
            return (handle, users)
        }.subscribeNext { [weak self] (handle, userModels) -> Void in
            guard let strongSelf = self else {
                return
            }
            if let imageUrl = handle.imageUrl {
                strongSelf.handleImageView.hidden = false
                strongSelf.populateWithImageUrl(imageUrl)
            }else{
                strongSelf.handleImageView.hidden = true
                if userModels.count > 1 {
                    let spriteUrls = userModels.filter({ $0.username != Constants.myUsername }).map({ $0.spriteUrl })
                    strongSelf.populateWithSpriteUrls(spriteUrls)
                }else{
                    let spriteUrls = userModels.filter({ $0.username == Constants.myUsername }).map({ $0.spriteUrl })
                    strongSelf.populateWithSpriteUrls(spriteUrls)
                }
            }

        }
        .addDisposableTo(disposeBag)


    }

    deinit {
        disposeBag = DisposeBag()
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        handleImageView.layer.cornerRadius = handleImageView.frame.size.width / 4
    }
    //http://www.kuqin.com/shuoit/20160330/351397.html

}