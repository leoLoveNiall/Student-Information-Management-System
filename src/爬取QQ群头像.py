import requests
import response
import json
import os

#https://blog.csdn.net/weixin_39966376/article/details/111623239
#https://www.cnblogs.com/haifeima/p/9962772.html

headers={'cookie':}
def get_data(num):
    data = {
        'gc': '869500258',
        'st': num*21,
        'end': num*21 + 20,
        'sort': '0',
        'bkn': '847024395'
        }
    return data

def get_con(url,data):
    requests.packages.urllib3.disable_warnings()
    response = response = requests.post(url, headers=headers, data=data)
    response.encoding = response.apparent_encoding
    text = json.loads(response.text)
    content = text['mems']
    for item in content:
        xinxi =  {
            'qqmem': item['uin'], #QQ群成员QQ号
            'beizhu': item['card'], #QQ群成员备注
            'name': item['nick'] #QQ群成员昵称
            }
        yield xinxi

def get_pic(url):
    for i in range(100):
        try:
            content = get_con(url=url, data = get_data(i))
            if not os.path.isdir('picture'):
                os.mkdir('picture')
            for item in content:
                pic_url = 'https://q4.qlogo.cn/g?b=qq&nk={}&s=140'.format(item['qqmem'])
                print(pic_url, item['beizhu'])
                abs_path = os.path.join('picture', '%s.jpg' %item['beizhu'])
                open(abs_path, 'wb').write(requests.get(pic_url, verify = False).content)
 
        except:
            pass



if __name__ == '__main__':
    url = 'https://qun.qq.com/cgi-bin/qun_mgr/search_group_members'
    get_pic(url)

